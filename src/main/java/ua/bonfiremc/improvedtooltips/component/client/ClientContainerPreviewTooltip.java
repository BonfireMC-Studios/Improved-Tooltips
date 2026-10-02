package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.component.ContainerPreviewTooltip;

import java.util.List;

public record ClientContainerPreviewTooltip(ContainerPreviewTooltip tooltip) implements ClientTooltipComponent {
    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath("improvedtooltips", "stretchable_container"), x, y, this.getWidth(font), this.getHeight(font) - 2, tooltip.color());

        List<ItemStack> stacks = tooltip.contents().itemCopies().toList();

        assemblerLabelsGoBrrr:
        for (int i = 0; i < this.tooltip.rows(); i++) {
            for (int j = 0; j < this.tooltip.cols(); j++) {
                int index = j + i * 9;

                if (index >= stacks.size()) break assemblerLabelsGoBrrr;

                graphics.item(stacks.get(index), x + j * 18 + 8, y + i * 18 + 8);
            }
        }
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return this.tooltip.rows() * 18 + 7 * 2 + 2;
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return this.tooltip.cols() * 18 + 7 * 2;
    }
}
