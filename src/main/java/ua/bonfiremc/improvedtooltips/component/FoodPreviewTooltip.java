package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public record FoodPreviewTooltip(int nutrition, int saturation) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        FoodProperties food = stack.get(DataComponents.FOOD);

        if (food != null && stack.has(DataComponents.CONSUMABLE)) {
            return Optional.of(new FoodPreviewTooltip(food.nutrition(), Math.round(food.saturation())));
        }

        return Optional.empty();
    }
}
