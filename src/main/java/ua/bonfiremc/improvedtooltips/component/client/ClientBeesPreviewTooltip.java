package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;

public class ClientBeesPreviewTooltip implements ClientTooltipComponent {
    public static final Identifier BEE_ACTIVE = ImprovedTooltips.id("bees_and_honey/bee_active");
    public static final Identifier BEE_INACTIVE = ImprovedTooltips.id("bees_and_honey/bee_inactive");

    private final int bees;
    private final int maxBees;

    public ClientBeesPreviewTooltip(int bees, int maxBees) {
        this.bees = bees;
        this.maxBees = maxBees;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        for (int i = 1; i <= this.maxBees; i++) {
            int xOffset = (i - 1) * 11;

            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, i <= this.bees ? BEE_ACTIVE : BEE_INACTIVE, x + xOffset, y, 9, 9);
        }
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return 11;
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return this.maxBees * 9 + (this.maxBees - 1) * 2;
    }
}
