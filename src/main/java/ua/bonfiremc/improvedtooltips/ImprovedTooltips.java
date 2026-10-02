package ua.bonfiremc.improvedtooltips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
import ua.bonfiremc.improvedtooltips.component.MapPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.client.ClientContainerPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.ContainerPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.client.ClientMapPreviewTooltip;

public class ImprovedTooltips implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTooltipComponentCallback.EVENT.register(component -> {
            if (component instanceof ContainerPreviewTooltip tooltip) {
                return new ClientContainerPreviewTooltip(tooltip);
            } else if (component instanceof MapPreviewTooltip tooltip) {
                return new ClientMapPreviewTooltip(tooltip);
            }
            return null;
        });
    }
}
