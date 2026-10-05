package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public record PaintingPreviewTooltip(PaintingVariant variant) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        Holder<PaintingVariant> variant = stack.get(DataComponents.PAINTING_VARIANT);

        if (variant != null) {
            return Optional.of(new PaintingPreviewTooltip(variant.value()));
        }

        return Optional.empty();
    }
}
