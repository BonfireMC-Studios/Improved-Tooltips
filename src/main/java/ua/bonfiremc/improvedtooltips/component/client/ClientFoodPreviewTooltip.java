package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;
import ua.bonfiremc.improvedtooltips.component.FoodPreviewTooltip;

public record ClientFoodPreviewTooltip(FoodPreviewTooltip tooltip) implements ClientTooltipComponent {
    public static final Identifier NUTRITION_FULL = ImprovedTooltips.id("food/nutrition_full");
    public static final Identifier NUTRITION_HALF = ImprovedTooltips.id("food/nutrition_half");
    public static final Identifier SATURATION_FULL = ImprovedTooltips.id("food/saturation_full");
    public static final Identifier SATURATION_HALF = ImprovedTooltips.id("food/saturation_half");

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        for (int i = 1; i <= Math.max(this.tooltip.nutrition(), this.tooltip.saturation()); i += 2) {
            int offset = ((i - 1) / 2) * 9;

            if (i <= this.tooltip.nutrition()) {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, i < this.tooltip.nutrition() ? NUTRITION_FULL : NUTRITION_HALF, x + offset, y, 9, 9);
            }

            if (i <= this.tooltip.saturation()) {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, i < this.tooltip.saturation() ? SATURATION_FULL : SATURATION_HALF, x + offset, y + (this.tooltip.nutrition() > 0 ? 11 : 0), 9, 9);
            }
        }
    }

    @Override
    public int getHeight(@NonNull Font font) {
        int height = 0;

        if (this.tooltip.nutrition() > 0) height += 11;
        if (this.tooltip.saturation() > 0) height += 11;

        return height;
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return Math.max(
            (this.tooltip.nutrition() + 1) / 2 * 9,
            (this.tooltip.saturation() + 1) / 2 * 9
        );
    }
}
