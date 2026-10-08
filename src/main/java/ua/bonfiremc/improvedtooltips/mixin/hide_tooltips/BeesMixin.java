package ua.bonfiremc.improvedtooltips.mixin.hide_tooltips;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Bees;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.bonfiremc.improvedtooltips.ITConfig;

import java.util.function.Consumer;

@Mixin(Bees.class)
public class BeesMixin {
    @Inject(method = "addToTooltip", at = @At("HEAD"), cancellable = true)
    public void hideTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components, CallbackInfo ci) {
        if (ITConfig.instance().beesPreview) {
            ci.cancel();
        }
    }
}
