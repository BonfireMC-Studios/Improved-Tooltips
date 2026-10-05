package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Bees;

import java.util.Optional;

public record BeesPreviewTooltip(int bees, int maxBees) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        Bees bees = stack.get(DataComponents.BEES);

        if (bees != null) {
            return Optional.of(new BeesPreviewTooltip(bees.bees().size(), 3));
        }

        return Optional.empty();
    }
}
