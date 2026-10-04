package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.List;

public record ComposeTooltip(List<TooltipComponent> components) implements TooltipComponent {
}
