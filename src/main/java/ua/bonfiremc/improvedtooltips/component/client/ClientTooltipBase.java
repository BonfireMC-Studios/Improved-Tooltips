package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
//? if >=1.21.6 {
import net.minecraft.client.renderer.RenderPipelines;
//?} else if >=1.21.2 {
/*import net.minecraft.client.renderer.RenderType;
*///?} else {
/*import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
*///?}

public abstract class ClientTooltipBase implements ClientTooltipComponent {
    //? if >=1.21.2
    @Override
    public abstract int getHeight(Font font);

    //? if <26.1 {
    /*public void extractImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
    }

    public void extractText(GuiGraphicsExtractor graphics, Font font, int x, int y) {
    }
    *///?}

    //? if >=1.21.6 <26.1 {
    /*@Override
    public void renderImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
        this.extractImage(font, x, y, w, h, graphics);
    }

    @Override
    public void renderText(GuiGraphicsExtractor graphics, Font font, int x, int y) {
        this.extractText(graphics, font, x, y);
    }
    *///?}

    //? if >=1.21.2 <1.21.6 {
    /*@Override
    public void renderImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
        this.extractImage(font, x, y, w, h, graphics);
        this.extractText(graphics, font, x, y);
    }
    *///?}

    //? if <1.21.2 {
    /*@Override
    public int getHeight() {
        return this.getHeight(Minecraft.getInstance().font);
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphicsExtractor graphics) {
        this.extractImage(font, x, y, 0, 0, graphics);
        this.extractText(graphics, font, x, y);
    }
    *///?}

    public static void sprite(GuiGraphicsExtractor graphics, Identifier sprite, int x, int y, int w, int h) {
        //? if >=1.21.6 {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, x, y, w, h);
        //?} else if >=1.21.2 {
        /*graphics.blitSprite(RenderType::guiTextured, sprite, x, y, w, h);
        *///?} else
        /*graphics.blitSprite(sprite, x, y, w, h);*/
    }

    public static void sprite(GuiGraphicsExtractor graphics, Identifier sprite, int x, int y, int w, int h, int color) {
        //? if >=1.21.6 {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, x, y, w, h, color);
        //?} else if >=1.21.2 {
        /*graphics.blitSprite(RenderType::guiTextured, sprite, x, y, w, h, color);
        *///?} else {
        /*setShaderColor(color);
        graphics.blitSprite(sprite, x, y, w, h);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        *///?}
    }

    public static void sprite(GuiGraphicsExtractor graphics, TextureAtlasSprite sprite, int x, int y, int w, int h) {
        //? if >=1.21.6 {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, sprite, x, y, w, h);
        //?} else if >=1.21.2 {
        /*graphics.blitSprite(RenderType::guiTextured, sprite, x, y, w, h);
        *///?} else
        /*graphics.blit(x, y, 0, w, h, sprite);*/
    }

    public static void item(GuiGraphicsExtractor graphics, ItemStack stack, int x, int y) {
        //? if >=26.1 {
        graphics.item(stack, x, y);
        //?} else
        //graphics.renderItem(stack, x, y);
    }

    public static void text(GuiGraphicsExtractor graphics, Font font, Component text, int x, int y, int color) {
        //? if >=26.1 {
        graphics.text(font, text, x, y, color);
        //?} else
        //graphics.drawString(font, text, x, y, color);
    }

    //? if <1.21.2 {
    /*public static void setShaderColor(int argb) {
        RenderSystem.setShaderColor(
            (argb >> 16 & 0xFF) / 255f,
            (argb >> 8 & 0xFF) / 255f,
            (argb & 0xFF) / 255f,
            (argb >>> 24) / 255f
        );
    }
    *///?}
}
