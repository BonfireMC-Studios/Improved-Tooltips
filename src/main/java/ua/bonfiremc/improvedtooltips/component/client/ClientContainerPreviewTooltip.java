package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.ITConfig;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;

import java.util.List;

public class ClientContainerPreviewTooltip extends ClientTooltipBase {
    private static final Identifier CONTAINER_BACKGROUND = ImprovedTooltips.id("stretchable_container");

    private final List<ItemStack> items;

    private final int cols;
    private final int rows;
    private final int color;

    private final int width;
    private final int height;

    public ClientContainerPreviewTooltip(ItemContainerContents contents, int cols, int rows, int color) {
        //? if >=26.3 {
        this.items = contents.itemCopies().toList();
        //?} else if >=26.1 {
        /*this.items = contents.allItemsCopyStream().toList();
        *///?} else
        //this.items = contents.stream().toList();

        this.cols = cols;
        this.rows = rows;
        this.color = ITConfig.instance().coloredShulkerBoxContainer ? color : -1;

        this.width = this.cols * 18 + 7 * 2;
        this.height = this.rows * 18 + 7 * 2;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        sprite(graphics, CONTAINER_BACKGROUND, x, y, this.width, this.height, this.color);

        assemblerLabelsGoBrrr:
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                int index = j + i * 9;

                if (index >= this.items.size()) break assemblerLabelsGoBrrr;

                item(graphics, this.items.get(index), x + j * 18 + 8, y + i * 18 + 8);
            }
        }
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
