package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.AtlasIds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import org.jspecify.annotations.NonNull;

public class ClientPaintingPreviewTooltip implements ClientTooltipComponent {
    private final PaintingVariant variant;
    private final TextureAtlasSprite sprite;

    private final int textureWidth;
    private final int textureHeight;

    public ClientPaintingPreviewTooltip(PaintingVariant variant) {
        this.variant = variant;
        this.sprite = Minecraft.getInstance().getAtlasManager().getAtlasOrThrow(AtlasIds.PAINTINGS).getSprite(variant.assetId());

        this.textureWidth = variant.width() * 16;
        this.textureHeight = variant.height() * 16;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, this.sprite, x, y, this.textureWidth, this.textureHeight);
    }

    @Override
    public void extractText(@NonNull GuiGraphicsExtractor graphics, @NonNull Font font, int x, int y) {
        int yOffset = 0;

        if (this.variant.title().isPresent()) {
            graphics.text(font, this.variant.title().get(), x + this.textureWidth + 4, y, -1);

            yOffset += font.lineHeight + 1;
        }
        if (this.variant.author().isPresent()) {
            graphics.text(font, this.variant.author().get(), x + this.textureWidth + 4, y + yOffset, -1);

            yOffset += font.lineHeight + 1;
        }

        graphics.text(font, this.getDimensionsText(), x + this.textureWidth + 4, y + yOffset, -1);
    }

    @Override
    public int getWidth(@NonNull Font font) {
        int textWidth = font.width(this.getDimensionsText());

        if (this.variant.title().isPresent()) {
            textWidth = Math.max(textWidth, font.width(this.variant.title().get()));
        }
        if (this.variant.author().isPresent()) {
            textWidth = Math.max(textWidth, font.width(this.variant.author().get()));
        }

        return this.textureWidth + 4 + textWidth;
    }

    @Override
    public int getHeight(@NonNull Font font) {
        int textHeight = font.lineHeight - 1;

        if (this.variant.title().isPresent()) {
            textHeight += font.lineHeight + 1;
        }
        if (this.variant.author().isPresent()) {
            textHeight += font.lineHeight + 1;
        }

        return Math.max(this.textureHeight, textHeight) + 2;
    }

    private Component getDimensionsText() {
        return Component.translatable("painting.dimensions", this.variant.width(), this.variant.height());
    }
}
