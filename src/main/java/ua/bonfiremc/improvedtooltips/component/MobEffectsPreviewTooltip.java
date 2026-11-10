package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
//? if <1.21.5
/*import net.minecraft.world.item.Items;*/
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record MobEffectsPreviewTooltip(List<MobEffectInstance> effects, float durationScale) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);

        if (contents != null) {
            List<MobEffectInstance> effects = new ArrayList<>();

            for (MobEffectInstance effect : contents.getAllEffects()) {
                effects.add(effect);
            }

            //? if >=1.21.5 {
            float durationScale = stack.getOrDefault(DataComponents.POTION_DURATION_SCALE, 1f);
            //?} else
            /*float durationScale = stack.is(Items.TIPPED_ARROW) ? 0.125f : stack.is(Items.LINGERING_POTION) ? 0.25f : 1f;*/

            return Optional.of(new MobEffectsPreviewTooltip(effects, durationScale));
        }

        return Optional.empty();
    }
}
