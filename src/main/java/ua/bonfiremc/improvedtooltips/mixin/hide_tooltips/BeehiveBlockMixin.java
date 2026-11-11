//? if >=1.21.2 <1.21.5 {
/*package ua.bonfiremc.improvedtooltips.mixin.hide_tooltips;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BeehiveBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ua.bonfiremc.improvedtooltips.ITConfig;
import ua.bonfiremc.improvedtooltips.component.HoneyPreviewTooltip;

import java.util.List;

@Mixin(BeehiveBlock.class)
public class BeehiveBlockMixin {
    @WrapOperation(method = "appendHoverText", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean hideBees(List<Object> lines, Object line, Operation<Boolean> original, @Local(argsOnly = true) ItemStack stack) {
        if (ITConfig.instance().beesPreview && stack.has(DataComponents.BEES)) return false;

        return original.call(lines, line);
    }

    @WrapOperation(method = "appendHoverText", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 1))
    private boolean hideHoney(List<Object> lines, Object line, Operation<Boolean> original, @Local(argsOnly = true) ItemStack stack) {
        if (ITConfig.instance().honeyPreview && HoneyPreviewTooltip.of(stack).isPresent()) return false;

        return original.call(lines, line);
    }
}
*///?}
