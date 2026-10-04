package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;

public class ClientHoneyPreviewTooltip implements ClientTooltipComponent {
    public static final Identifier HONEY_ACTIVE = ImprovedTooltips.id("bees_and_honey/honey_active");
    public static final Identifier HONEY_INACTIVE = ImprovedTooltips.id("bees_and_honey/honey_inactive");

    private final int honey;
    private final int maxHoney;

    public ClientHoneyPreviewTooltip(int honey, int maxHoney) {
        this.honey = honey;
        this.maxHoney = maxHoney;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        for (int i = 1; i <= this.maxHoney; i++) {
            int xOffset = (i - 1) * 11;

            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, i <= this.honey ? HONEY_ACTIVE : HONEY_INACTIVE, x + xOffset, y, 9, 9);
        }
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return 11;
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return this.maxHoney * 9 + (this.maxHoney - 1) * 2;
    }
}
