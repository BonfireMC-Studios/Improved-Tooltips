package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import org.jspecify.annotations.NonNull;

import java.util.List;

public record ClientComposeTooltip(List<ClientTooltipComponent> components) implements ClientTooltipComponent {
    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        int yOffset = 0;

        for (ClientTooltipComponent component : this.components) {
            component.extractImage(font, x, y + yOffset, w, h, graphics);

            yOffset += component.getHeight(font);
        }
    }

    @Override
    public void extractText(@NonNull GuiGraphicsExtractor graphics, @NonNull Font font, int x, int y) {
        int yOffset = 0;

        for (ClientTooltipComponent component : this.components) {
            component.extractText(graphics, font, x, y + yOffset);

            yOffset += component.getHeight(font);
        }
    }

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
            height += component.getHeight(font);
        }

        return height;
    }
}
