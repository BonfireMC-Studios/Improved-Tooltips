package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.data.AtlasIds;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.component.PaintingPreviewTooltip;

public record ClientPaintingPreviewTooltip(PaintingPreviewTooltip tooltip) implements ClientTooltipComponent {
    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        TextureAtlas paintings = Minecraft.getInstance().getAtlasManager().getAtlasOrThrow(AtlasIds.PAINTINGS);

        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, paintings.getSprite(tooltip.variant().assetId()), x, y, this.getWidth(font), this.getHeight(font) - 2);
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return tooltip.variant().height() * 16 + 2;
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return tooltip.variant().width() * 16;
    }
}
