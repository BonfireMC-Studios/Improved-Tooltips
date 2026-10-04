package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

public record HoneyPreviewTooltip(int honey, int maxHoney) implements TooltipComponent {
}
