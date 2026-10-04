package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.level.saveddata.maps.MapId;

public record MapPreviewTooltip(MapId id) implements TooltipComponent {
}
