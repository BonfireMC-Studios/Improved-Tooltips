package ua.bonfiremc.improvedtooltips.mixin.hide_tooltips;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(PotionContents.class)
public class PotionContentsMixin {
    @Inject(method = "addPotionTooltip", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 0), cancellable = true)
    private static void hideTooltip(Iterable<MobEffectInstance> effects, Consumer<Component> lines, float durationScale, float tickrate, CallbackInfo ci) {
        ci.cancel();
    }
}
