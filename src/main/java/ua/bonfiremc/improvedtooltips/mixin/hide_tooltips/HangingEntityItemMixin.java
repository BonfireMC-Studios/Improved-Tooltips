package ua.bonfiremc.improvedtooltips.mixin.hide_tooltips;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.bonfiremc.improvedtooltips.ITConfig;
//? if >=1.21.5 {
import net.minecraft.world.item.component.TooltipDisplay;
import java.util.function.Consumer;
//?} else {
/*import ua.bonfiremc.improvedtooltips.component.PaintingPreviewTooltip;
import java.util.List;
*///?}

@Mixin(HangingEntityItem.class)
public class HangingEntityItemMixin {
    //? if >=1.21.11 {
    @Inject(method = "appendHoverText", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/painting/PaintingVariant;title()Ljava/util/Optional;"), cancellable = true)
    public void hideTooltip(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag, CallbackInfo ci) {
        if (ITConfig.instance().paintingPreview) {
            ci.cancel();
        }
    }
    //?} else if >=1.21.5 {
    /*@Inject(method = "appendHoverText", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/PaintingVariant;title()Ljava/util/Optional;"), cancellable = true)
    public void hideTooltip(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag, CallbackInfo ci) {
        if (ITConfig.instance().paintingPreview) {
            ci.cancel();
        }
    }
    *///?} else {
    /*@Inject(method = "appendHoverText", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/CustomData;read(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/MapDecoder;)Lcom/mojang/serialization/DataResult;"), cancellable = true)
    public void hideTooltip(ItemStack itemStack, Item.TooltipContext context, List<Component> lines, TooltipFlag tooltipFlag, CallbackInfo ci) {
        if (ITConfig.instance().paintingPreview && PaintingPreviewTooltip.of(itemStack).isPresent()) {
            ci.cancel();
        }
    }
    *///?}
}
