package ua.bonfiremc.improvedtooltips.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import ua.bonfiremc.improvedtooltips.component.ComposeTooltip;
import ua.bonfiremc.improvedtooltips.event.TooltipImageCallback;
import ua.bonfiremc.improvedtooltips.event.TooltipImageEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract DataComponentMap getComponents();

    @ModifyReturnValue(method = "getTooltipImage", at = @At("RETURN"))
    public Optional<TooltipComponent> improvedTooltips$overrideTooltip(Optional<TooltipComponent> original) {
        List<TooltipComponent> components = new ArrayList<>();

        original.ifPresent(components::add);

        for (TypedDataComponent<?> typedComponent : this.getComponents()) {
            TooltipComponent component = this.getTooltipComponent(typedComponent);

            if (component != null) {
                components.add(component);
            }
        }

        if (components.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(components.size() > 1
            ? new ComposeTooltip(components)
            : components.getFirst()
        );
    }

    @Unique
    private <T> @Nullable TooltipComponent getTooltipComponent(TypedDataComponent<T> component) {
        Event<TooltipImageCallback<T>> event = TooltipImageEvents.get(component.type());

        if (event != null) {
            return event.invoker().getImage((ItemStack) (Object) this, component.value());
        }
        return null;
    }

    @WrapWithCondition(method = "addDetailsToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V", ordinal = 5))
    public boolean improvedTooltips$hideContainerDetails1(ItemStack instance, DataComponentType<?> type, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
        return this.getItem() != Items.SHULKER_BOX && !Items.DYED_SHULKER_BOX.asList().contains(this.getItem());
    }
}
