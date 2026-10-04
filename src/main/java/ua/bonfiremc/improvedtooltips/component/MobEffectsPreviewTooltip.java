package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.List;

public record MobEffectsPreviewTooltip(List<MobEffectInstance> effects, float durationScale) implements TooltipComponent {
}
