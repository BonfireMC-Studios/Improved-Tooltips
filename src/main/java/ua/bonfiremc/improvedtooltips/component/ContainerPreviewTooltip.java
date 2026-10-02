package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.component.ItemContainerContents;

public record ContainerPreviewTooltip(ItemContainerContents contents, int cols, int rows, int color) implements TooltipComponent {
}
