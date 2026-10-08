package ua.bonfiremc.improvedtooltips.mixin;

import com.mojang.renderpearl.api.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ua.bonfiremc.improvedtooltips.ITConfig;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;
import ua.bonfiremc.improvedtooltips.goose.ColorExtractor;

@Mixin(TooltipRenderUtil.class)
public class TooltipRenderUtilMixin {
    @Shadow
    @Final
    private static Identifier BACKGROUND_SPRITE;

    @Shadow
    @Final
    private static Identifier FRAME_SPRITE;

    @Redirect(method = "extractTooltipBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/renderpearl/api/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private static void improvedTooltips$changeTooltipColor(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height) {
        if (ITConfig.instance().coloredTooltips && instance instanceof ColorExtractor extractor && improvedTooltips$isDefaultTooltip(location)) {
            instance.blitSprite(renderPipeline, ImprovedTooltips.id(location.getPath()), x, y, width, height, extractor.improvedTooltips$getTooltipColor());
        } else {
            instance.blitSprite(renderPipeline, location, x, y, width, height);
        }
    }

    @Unique
    private static boolean improvedTooltips$isDefaultTooltip(Identifier identifier) {
        return identifier.equals(BACKGROUND_SPRITE) || identifier.equals(FRAME_SPRITE);
    }
}
