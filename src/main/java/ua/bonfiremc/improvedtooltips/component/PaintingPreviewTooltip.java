package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

public record PaintingPreviewTooltip(PaintingVariant variant) implements TooltipComponent {
}
