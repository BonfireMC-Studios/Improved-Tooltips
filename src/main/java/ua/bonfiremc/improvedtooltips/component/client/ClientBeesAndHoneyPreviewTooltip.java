package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;
import ua.bonfiremc.improvedtooltips.component.BeesAndHoneyPreviewTooltip;

public record ClientBeesAndHoneyPreviewTooltip(BeesAndHoneyPreviewTooltip tooltip) implements ClientTooltipComponent {
    public static final Identifier BEE_ACTIVE = ImprovedTooltips.id("bees_and_honey/bee_active");
    public static final Identifier BEE_INACTIVE = ImprovedTooltips.id("bees_and_honey/bee_inactive");
    public static final Identifier HONEY_ACTIVE = ImprovedTooltips.id("bees_and_honey/honey_active");
    public static final Identifier HONEY_INACTIVE = ImprovedTooltips.id("bees_and_honey/honey_inactive");

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        for (int i = 1; i <= 5; i++) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, i <= tooltip.bees() ? HONEY_ACTIVE : HONEY_INACTIVE, x + (i - 1) * 11, y + 11, 9, 9);

            if (i <= 3) {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, i <= tooltip.honey() ? BEE_ACTIVE : BEE_INACTIVE, x + (i - 1) * 11, y, 9, 9);
            }
        }
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return 22;
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return 53;
    }
}
