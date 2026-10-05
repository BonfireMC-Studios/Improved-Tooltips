package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;

public class ClientFoodPreviewTooltip implements ClientTooltipComponent {
    public static final Identifier NUTRITION_FULL = Identifier.withDefaultNamespace("hud/food_full");
    public static final Identifier NUTRITION_HALF = Identifier.withDefaultNamespace("hud/food_half");

    public static final Identifier SATURATION_FULL = ImprovedTooltips.id("food/saturation_full");
    public static final Identifier SATURATION_HALF = ImprovedTooltips.id("food/saturation_half");

    private final int nutrition;
    private final int saturation;

    public ClientFoodPreviewTooltip(int nutrition, int saturation) {
        this.nutrition = nutrition;
        this.saturation = saturation;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        for (int i = 1; i <= Math.max(this.nutrition, this.saturation); i += 2) {
            int offset = ((i - 1) / 2) * 9;

            if (i <= this.nutrition) {
                graphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    i < this.nutrition ? NUTRITION_FULL : NUTRITION_HALF,
                    x + offset, y,
                    9, 9
                );
            }

            if (i <= this.saturation) {
                graphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    i < this.saturation ? SATURATION_FULL : SATURATION_HALF,
                    x + offset, y + (this.nutrition > 0 ? 11 : 0),
                    9, 9
                );
            }
        }
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return Math.max(
            (this.nutrition + 1) / 2 * 9,
            (this.saturation + 1) / 2 * 9
        );
    }

    @Override
    public int getHeight(@NonNull Font font) {
        int height = 0;

        if (this.nutrition > 0) height += 9;
        if (this.saturation > 0) height += 9;
        if (this.nutrition > 0 && this.saturation > 0) height += 2;

        return height + 2;
    }
}
