package ua.bonfiremc.improvedtooltips.event;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public interface TooltipImageCallback<T> {
    TooltipComponent getImage(ItemStack stack, T component);
}
