package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import ua.bonfiremc.improvedtooltips.ITConfig;

import java.util.Optional;

public record FoodPreviewTooltip(int nutrition, int saturation) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        FoodProperties food = stack.get(DataComponents.FOOD);

        if (food != null && stack.has(DataComponents.CONSUMABLE)) {
            int nutrition = food.nutrition();
            int saturation = Math.round(food.saturation());

            if (nutrition > 0 || (ITConfig.instance().saturationPreview && saturation > 0)) {
                return Optional.of(new FoodPreviewTooltip(nutrition, saturation));
            }
        }

        return Optional.empty();
    }
}
