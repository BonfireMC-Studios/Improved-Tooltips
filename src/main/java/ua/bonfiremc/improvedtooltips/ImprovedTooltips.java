package ua.bonfiremc.improvedtooltips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
import net.minecraft.resources.Identifier;
import ua.bonfiremc.improvedtooltips.component.BeesAndHoneyPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.ContainerPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.FoodPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.MapPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.client.ClientBeesAndHoneyPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.client.ClientContainerPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.client.ClientFoodPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.client.ClientMapPreviewTooltip;

public class ImprovedTooltips implements ClientModInitializer {
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath("improvedtooltips", path);
    }

    @Override
    public void onInitializeClient() {
        ClientTooltipComponentCallback.EVENT.register(component -> switch (component) {
            case BeesAndHoneyPreviewTooltip tooltip -> new ClientBeesAndHoneyPreviewTooltip(tooltip);
            case ContainerPreviewTooltip tooltip -> new ClientContainerPreviewTooltip(tooltip);
            case FoodPreviewTooltip tooltip -> new ClientFoodPreviewTooltip(tooltip);
            case MapPreviewTooltip tooltip -> new ClientMapPreviewTooltip(tooltip);
            default -> null;
        });
    }
}
