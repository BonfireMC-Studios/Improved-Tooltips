package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.BeehiveBlock;

import java.util.Optional;

public record HoneyPreviewTooltip(int honey, int maxHoney) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        BlockItemStateProperties state = stack.get(DataComponents.BLOCK_STATE);

        if (state != null) {
            Integer honey = state.get(BeehiveBlock.HONEY_LEVEL);

            if (honey != null) {
                return Optional.of(new HoneyPreviewTooltip(honey, BeehiveBlock.MAX_HONEY_LEVELS));
            }
        }

        return Optional.empty();
    }
}
