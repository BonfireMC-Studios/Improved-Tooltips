package ua.bonfiremc.improvedtooltips.mixin.hide_tooltips;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(HangingEntityItem.class)
public class HangingEntityItemMixin {
    @Inject(method = "appendHoverText", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/painting/PaintingVariant;title()Ljava/util/Optional;"), cancellable = true)
    public void hideTooltip(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag, CallbackInfo ci) {
        ci.cancel();
    }
}
