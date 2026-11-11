//? if <1.21.5 {
/*package ua.bonfiremc.improvedtooltips.mixin.hide_tooltips;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ua.bonfiremc.improvedtooltips.ITConfig;

import java.util.List;

@Mixin(ShulkerBoxBlock.class)
public class ShulkerBoxBlockMixin {
    @ModifyExpressionValue(method = "appendHoverText", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/ItemContainerContents;nonEmptyItems()Ljava/lang/Iterable;"))
    private Iterable<ItemStack> hideTooltip(Iterable<ItemStack> items) {
        return ITConfig.instance().shulkerBoxContentPreview ? List.of() : items;
    }
}
*///?}
