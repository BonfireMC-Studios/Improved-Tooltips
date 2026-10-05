package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;

import java.util.Optional;

public record ContainerPreviewTooltip(ItemContainerContents contents, int cols, int rows, int color) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        ItemContainerContents contents = stack.get(DataComponents.CONTAINER);

        if (contents != null) {
            boolean isShulkerBox = stack.is(Items.SHULKER_BOX);
            boolean isDyedSB = Items.DYED_SHULKER_BOX.asList().contains(stack.getItem());

            if (isShulkerBox || isDyedSB) {
                int color = 0xFF976797;

                if (isDyedSB) {
                    for (DyeColor dye : DyeColor.values()) {
                        if (Items.DYED_SHULKER_BOX.pick(dye) == stack.getItem()) {
                            color = dye.getTextureDiffuseColor();
                            break;
                        }
                    }
                }

                return Optional.of(new ContainerPreviewTooltip(contents, 9, 3, color));
            }
        }

        return Optional.empty();
    }
}
