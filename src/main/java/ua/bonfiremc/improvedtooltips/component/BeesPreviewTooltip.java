package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
//? if >=1.21.5 {
import net.minecraft.world.item.component.Bees;
//?} else {
/*import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import java.util.List;
*///?}

import java.util.Optional;

public record BeesPreviewTooltip(int bees, int maxBees) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        //? if >=1.21.5 {
        Bees component = stack.get(DataComponents.BEES);
        var bees = component != null ? component.bees() : null;
        //?} else
        /*List<BeehiveBlockEntity.Occupant> bees = stack.get(DataComponents.BEES);*/

        if (bees != null) {
            return Optional.of(new BeesPreviewTooltip(bees.size(), 3));
        }

        return Optional.empty();
    }
}
