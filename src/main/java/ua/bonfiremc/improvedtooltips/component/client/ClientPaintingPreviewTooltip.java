package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.data.AtlasIds;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import org.jspecify.annotations.NonNull;

public class ClientPaintingPreviewTooltip implements ClientTooltipComponent {
    private final TextureAtlasSprite sprite;

    private final int width;
    private final int height;

    public ClientPaintingPreviewTooltip(PaintingVariant variant) {
        this.sprite = Minecraft.getInstance().getAtlasManager().get(new SpriteId(AtlasIds.PAINTINGS, variant.assetId()));

        this.width = variant.width() * 16;
        this.height = variant.height() * 16;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, this.sprite, x, y, this.width, this.height);
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return this.width;
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return this.height + 2;
    }
}
