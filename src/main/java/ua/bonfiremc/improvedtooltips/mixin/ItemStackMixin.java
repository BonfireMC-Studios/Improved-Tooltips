package ua.bonfiremc.improvedtooltips.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.bonfiremc.improvedtooltips.component.ContainerPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.MapPreviewTooltip;

import java.util.Optional;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract DataComponentMap getComponents();

    @Inject(method = "getTooltipImage", at = @At("HEAD"), cancellable = true)
    public void improvedTooltips$overrideTooltip(CallbackInfoReturnable<Optional<TooltipComponent>> cir) {
        if (this.getItem() == Items.FILLED_MAP) {
            cir.setReturnValue(Optional.of(new MapPreviewTooltip(this.getComponents().get(DataComponents.MAP_ID))));
            return;
        }

        boolean isShulkerBox = this.getItem() == Items.SHULKER_BOX;
        boolean isDyedShulkerBox = Items.DYED_SHULKER_BOX.asList().contains(this.getItem());

        if (isShulkerBox || isDyedShulkerBox) {
            int color = 0xFF976797;

            if (isDyedShulkerBox) {
                for (DyeColor dye : DyeColor.values()) {
                    if (Items.DYED_SHULKER_BOX.pick(dye) == this.getItem()) {
                        color = dye.getTextureDiffuseColor();
                        break;
                    }
                }
            }

            cir.setReturnValue(Optional.of(new ContainerPreviewTooltip(this.getComponents().get(DataComponents.CONTAINER), 9, 3, color)));
        }
    }

    @WrapWithCondition(method = "addDetailsToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V", ordinal = 5))
    public boolean improvedTooltips$hideContainerDetails(ItemStack instance, DataComponentType<?> type, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
        return this.getItem() != Items.SHULKER_BOX && !Items.DYED_SHULKER_BOX.asList().contains(this.getItem());
    }
}
