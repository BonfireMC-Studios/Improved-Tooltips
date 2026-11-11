package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import ua.bonfiremc.improvedtooltips.ITConfig;

import java.util.Optional;

public record ContainerPreviewTooltip(ItemContainerContents contents, int cols, int rows, int color) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        ItemContainerContents contents = stack.get(DataComponents.CONTAINER);

        if (contents != null) {
            if (!ITConfig.instance().emptyShulkerBoxContent && !contents.nonEmptyItems().iterator().hasNext()) return Optional.empty();

            if (isShulkerBox(stack.getItem())) {
                DyeColor dye = ((ShulkerBoxBlock) Block.byItem(stack.getItem())).getColor();
                int color = dye != null ? dye.getTextureDiffuseColor() : 0xFF976797;

                return Optional.of(new ContainerPreviewTooltip(contents, 9, 3, color));
            }
        }

        return Optional.empty();
    }

    public static boolean isShulkerBox(Item item) {
        return Block.byItem(item) instanceof ShulkerBoxBlock;
    }
}
