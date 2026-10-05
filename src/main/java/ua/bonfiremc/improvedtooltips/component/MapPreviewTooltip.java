package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.maps.MapId;

import java.util.Optional;

public record MapPreviewTooltip(MapId id) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        MapId id = stack.get(DataComponents.MAP_ID);

        if (id != null) {
            return Optional.of(new MapPreviewTooltip(id));
        }

        return Optional.empty();
    }
}
