package ua.bonfiremc.improvedtooltips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import ua.bonfiremc.improvedtooltips.component.*;
import ua.bonfiremc.improvedtooltips.component.client.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ImprovedTooltips implements ClientModInitializer {
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath("improvedtooltips", path);
    }

    public static void addComponents(ItemStack stack, Consumer<TooltipComponent> consumer) {
        ITConfig config = ITConfig.instance();

        if (config.beesPreview) {
            BeesPreviewTooltip.of(stack).ifPresent(consumer);
        }
        if (config.shulkerBoxContentPreview) {
            ContainerPreviewTooltip.of(stack).ifPresent(consumer);
        }
        if (config.foodPreview) {
            FoodPreviewTooltip.of(stack).ifPresent(consumer);
        }
        if (config.honeyPreview) {
            HoneyPreviewTooltip.of(stack).ifPresent(consumer);
        }
        if (config.mapPreview) {
            MapPreviewTooltip.of(stack).ifPresent(consumer);
        }
        if (config.mobEffectsPreview) {
            MobEffectsPreviewTooltip.of(stack).ifPresent(consumer);
        }
        if (config.paintingPreview) {
            PaintingPreviewTooltip.of(stack).ifPresent(consumer);
        }
    }

    @Override
    public void onInitializeClient() {
        ITConfig.HANDLER.load();
        ClientTooltipComponentCallback.EVENT.register(this::toClientComponent);
    }

    private ClientTooltipComponent toClientComponent(TooltipComponent component) {
        return switch (component) {
            case BeesPreviewTooltip tooltip -> new ClientBeesPreviewTooltip(tooltip.bees(), tooltip.maxBees());
            case ContainerPreviewTooltip tooltip -> new ClientContainerPreviewTooltip(tooltip.contents(), tooltip.cols(), tooltip.rows(), tooltip.color());
            case FoodPreviewTooltip tooltip -> new ClientFoodPreviewTooltip(tooltip.nutrition(), tooltip.saturation());
            case HoneyPreviewTooltip tooltip -> new ClientHoneyPreviewTooltip(tooltip.honey(), tooltip.maxHoney());
            case MapPreviewTooltip tooltip -> new ClientMapPreviewTooltip(tooltip.id());
            case MobEffectsPreviewTooltip tooltip -> new ClientMobEffectsPreviewTooltip(tooltip.effects(), tooltip.durationScale());
            case PaintingPreviewTooltip tooltip -> new ClientPaintingPreviewTooltip(tooltip.variant());

            case ComposeTooltip tooltip -> {
                List<ClientTooltipComponent> tooltips = new ArrayList<>();

                for (TooltipComponent c : tooltip.components()) {
                    tooltips.add(this.toClientComponent(c));
                }

                yield new ClientComposeTooltip(tooltips);
            }
            default -> null;
        };
    }
}
