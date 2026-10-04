package ua.bonfiremc.improvedtooltips.event;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public interface TooltipImageCallback<T> {
    @Nullable TooltipComponent getImage(ItemStack stack, T component);
}
