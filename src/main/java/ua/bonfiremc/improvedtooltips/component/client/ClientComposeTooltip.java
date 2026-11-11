package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ClientComposeTooltip extends ClientTooltipBase {
    private final List<ClientTooltipComponent> components;

    public ClientComposeTooltip(List<ClientTooltipComponent> components) {
        this.components = components;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        int yOffset = 0;

        for (ClientTooltipComponent component : this.components) {
            //? if >=26.1 {
            component.extractImage(font, x, y + yOffset, w, h, graphics);
            //?} else if >=1.21.2 {
            /*component.renderImage(font, x, y + yOffset, w, h, graphics);
            *///?} else
            /*component.renderImage(font, x, y + yOffset, graphics);*/

            yOffset += height(component, font);
        }
    }

    //? if >=1.21.6 {
    @Override
    public void extractText(@NonNull GuiGraphicsExtractor graphics, @NonNull Font font, int x, int y) {
        int yOffset = 0;

        for (ClientTooltipComponent component : this.components) {
            //? if >=26.1 {
            component.extractText(graphics, font, x, y + yOffset);
            //?} else
            //component.renderText(graphics, font, x, y + yOffset);

            yOffset += height(component, font);
        }
    }
    //?}

    @Override
    public int getWidth(@NonNull Font font) {
        int width = 0;

        for (ClientTooltipComponent component : this.components) {
            width = Math.max(width, component.getWidth(font));
        }

        return width;
    }

    @Override
    public int getHeight(@NonNull Font font) {
        int height = 0;

        for (ClientTooltipComponent component : this.components) {
            height += height(component, font);
        }

        return height;
    }

    private static int height(ClientTooltipComponent component, Font font) {
        //? if >=1.21.2 {
        return component.getHeight(font);
        //?} else
        /*return component.getHeight();*/
    }
}
