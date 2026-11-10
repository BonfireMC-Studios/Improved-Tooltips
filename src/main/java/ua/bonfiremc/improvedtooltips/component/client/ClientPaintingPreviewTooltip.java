package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//? if >=1.21.9 {
import net.minecraft.data.AtlasIds;
//?}
import net.minecraft.network.chat.Component;
//? if >=1.21.11 {
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
//?} else
/*import net.minecraft.world.entity.decoration.PaintingVariant;*/
import ua.bonfiremc.improvedtooltips.component.PaintingPreviewTooltip;
import org.jspecify.annotations.NonNull;

public class ClientPaintingPreviewTooltip extends ClientTooltipBase {
    private final PaintingPreviewTooltip tooltip;
    private final PaintingVariant variant;
    private final TextureAtlasSprite sprite;

    private final int textureWidth;
    private final int textureHeight;

    public ClientPaintingPreviewTooltip(PaintingPreviewTooltip tooltip) {
        PaintingVariant variant = tooltip.variant();

        this.tooltip = tooltip;
        this.variant = variant;
        //? if >=1.21.9 {
        this.sprite = Minecraft.getInstance().getAtlasManager().getAtlasOrThrow(AtlasIds.PAINTINGS).getSprite(variant.assetId());
        //?} else
        /*this.sprite = Minecraft.getInstance().getPaintingTextures().get(variant);*/

        this.textureWidth = variant.width() * 16;
        this.textureHeight = variant.height() * 16;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        sprite(graphics, this.sprite, x, y, this.textureWidth, this.textureHeight);
    }

    @Override
    public void extractText(@NonNull GuiGraphicsExtractor graphics, @NonNull Font font, int x, int y) {
        int yOffset = 0;

        if (this.tooltip.title().isPresent()) {
            text(graphics, font, this.tooltip.title().get(), x + this.textureWidth + 4, y, -1);

            yOffset += font.lineHeight + 1;
        }
        if (this.tooltip.author().isPresent()) {
            text(graphics, font, this.tooltip.author().get(), x + this.textureWidth + 4, y + yOffset, -1);

            yOffset += font.lineHeight + 1;
        }

        text(graphics, font, this.getDimensionsText(), x + this.textureWidth + 4, y + yOffset, -1);
    }

    @Override
    public int getWidth(@NonNull Font font) {
        int textWidth = font.width(this.getDimensionsText());

        if (this.tooltip.title().isPresent()) {
            textWidth = Math.max(textWidth, font.width(this.tooltip.title().get()));
        }
        if (this.tooltip.author().isPresent()) {
            textWidth = Math.max(textWidth, font.width(this.tooltip.author().get()));
        }

        return this.textureWidth + 4 + textWidth;
    }

    @Override
    public int getHeight(@NonNull Font font) {
        int textHeight = font.lineHeight - 1;

        if (this.tooltip.title().isPresent()) {
            textHeight += font.lineHeight + 1;
        }
        if (this.tooltip.author().isPresent()) {
            textHeight += font.lineHeight + 1;
        }

        return Math.max(this.textureHeight, textHeight) + 2;
    }

    private Component getDimensionsText() {
        return Component.translatable("painting.dimensions", this.variant.width(), this.variant.height());
    }
}
