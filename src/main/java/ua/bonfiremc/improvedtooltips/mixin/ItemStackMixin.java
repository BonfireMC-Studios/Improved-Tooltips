package ua.bonfiremc.improvedtooltips.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Bees;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.BeehiveBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ua.bonfiremc.improvedtooltips.component.BeesAndHoneyPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.ContainerPreviewTooltip;
import ua.bonfiremc.improvedtooltips.component.FoodPreviewTooltip;
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
        FoodProperties food = this.getComponents().get(DataComponents.FOOD);

        if (food != null) {
            cir.setReturnValue(Optional.of(new FoodPreviewTooltip(food.nutrition(), Math.round(food.saturation()))));
            return;
        }

        if (this.getItem() == Items.FILLED_MAP) {
            cir.setReturnValue(Optional.of(new MapPreviewTooltip(this.getComponents().get(DataComponents.MAP_ID))));
            return;
        }

        Bees bees = this.getComponents().get(DataComponents.BEES);

        if (bees != null) {
            BlockItemStateProperties properties = this.getComponents().get(DataComponents.BLOCK_STATE);

            if (properties != null) {
                Integer honey = properties.get(BeehiveBlock.HONEY_LEVEL);

                if (honey != null) {
                    cir.setReturnValue(Optional.of(new BeesAndHoneyPreviewTooltip(honey, bees.bees().size())));
                    return;
                }
            }
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
    public boolean improvedTooltips$hideContainerDetails1(ItemStack instance, DataComponentType<?> type, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
        return this.getItem() != Items.SHULKER_BOX && !Items.DYED_SHULKER_BOX.asList().contains(this.getItem());
    }

    @WrapWithCondition(method = "addDetailsToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V", ordinal = 3))
    public boolean improvedTooltips$hideContainerDetails2(ItemStack instance, DataComponentType<?> type, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
        return false;
    }

    @WrapWithCondition(method = "addDetailsToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V", ordinal = 23))
    public boolean improvedTooltips$hideContainerDetails3(ItemStack instance, DataComponentType<?> type, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
        return this.getItem() != Items.BEE_NEST && this.getItem() != Items.BEEHIVE;
    }
}
