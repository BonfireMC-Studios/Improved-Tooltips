package ua.bonfiremc.improvedtooltips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.BeehiveBlock;
import ua.bonfiremc.improvedtooltips.component.*;
import ua.bonfiremc.improvedtooltips.component.client.*;
import ua.bonfiremc.improvedtooltips.event.TooltipImageEvents;

import java.util.ArrayList;
import java.util.List;

public class ImprovedTooltips implements ClientModInitializer {
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath("improvedtooltips", path);
    }

    @Override
    public void onInitializeClient() {
        ClientTooltipComponentCallback.EVENT.register(this::toClientComponent);

        TooltipImageEvents.getOrCreate(DataComponents.BEES).register((_, component) ->
            new BeesPreviewTooltip(component.bees().size(), 3)
        );
        TooltipImageEvents.getOrCreate(DataComponents.CONTAINER).register((stack, component) -> {
            boolean isShulkerBox = stack.is(Items.SHULKER_BOX);
            boolean isDyedSB = Items.DYED_SHULKER_BOX.asList().contains(stack.getItem());

            if (isShulkerBox || isDyedSB) {
                int color = 0xFF976797;

                if (isDyedSB) {
                    for (DyeColor dye : DyeColor.values()) {
                        if (Items.DYED_SHULKER_BOX.pick(dye) == stack.getItem()) {
                            color = dye.getTextureDiffuseColor();
                            break;
                        }
                    }
                }

                return new ContainerPreviewTooltip(component, 9, 3, color);
            }

            return null;
        });
        TooltipImageEvents.getOrCreate(DataComponents.FOOD).register((stack, component) ->
            stack.getComponents().has(DataComponents.CONSUMABLE)
                ? new FoodPreviewTooltip(component.nutrition(), Math.round(component.saturation()))
                : null
        );
        TooltipImageEvents.getOrCreate(DataComponents.BLOCK_STATE).register((_, component) -> {
            Integer honey = component.get(BeehiveBlock.HONEY_LEVEL);

            return honey != null
                ? new HoneyPreviewTooltip(honey, BeehiveBlock.MAX_HONEY_LEVELS)
                : null;
        });
        TooltipImageEvents.getOrCreate(DataComponents.MAP_ID).register((_, component) ->
            new MapPreviewTooltip(component)
        );
        TooltipImageEvents.getOrCreate(DataComponents.POTION_CONTENTS).register((stack, component) -> {
            List<MobEffectInstance> effects = new ArrayList<>();

            for (MobEffectInstance effect : component.getAllEffects()) {
                effects.add(effect);
            }

            return new MobEffectsPreviewTooltip(effects, stack.getComponents().getOrDefault(DataComponents.POTION_DURATION_SCALE, 1f));
        });
        TooltipImageEvents.getOrCreate(DataComponents.PAINTING_VARIANT).register((_, component) ->
            new PaintingPreviewTooltip(component.value())
        );
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
