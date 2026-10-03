package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

public record FoodPreviewTooltip(int nutrition, int saturation) implements TooltipComponent {
}
