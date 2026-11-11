package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

import java.util.Optional;

public record MapPreviewTooltip(MapId id, MapItemSavedData data) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        MapId id = stack.get(DataComponents.MAP_ID);

        if (id != null) {
            Level level = Minecraft.getInstance().level;

            if (level != null) {
                return Optional.of(new MapPreviewTooltip(id, MapItem.getSavedData(id, level)));
            }
        }

        return Optional.empty();
    }
}
