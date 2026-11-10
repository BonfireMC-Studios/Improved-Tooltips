package ua.bonfiremc.improvedtooltips.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import ua.bonfiremc.improvedtooltips.ITConfig;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;
import ua.bonfiremc.improvedtooltips.goose.ColorExtractor;
//? if >=1.21.2 {
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Redirect;
//?} else {
/*import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.bonfiremc.improvedtooltips.component.client.ClientTooltipBase;
*///?}
//? if >=1.21.6 {
import com.mojang.renderpearl.api.pipeline.RenderPipeline;
//?} else if >=1.21.2 {
/*import net.minecraft.client.renderer.RenderType;
import java.util.function.Function;
*///?}

@Mixin(TooltipRenderUtil.class)
public class TooltipRenderUtilMixin {
    //? if >=1.21.2 {
    @Shadow
    @Final
    private static Identifier BACKGROUND_SPRITE;

    @Shadow
    @Final
    private static Identifier FRAME_SPRITE;

    //? if >=26.1 {
    @Redirect(method = "extractTooltipBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/renderpearl/api/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    //?} else if >=1.21.6 {
    /*@Redirect(method = "renderTooltipBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/renderpearl/api/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    *///?} else
    /*@Redirect(method = "renderTooltipBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/Identifier;IIII)V"))*/
    //? if >=1.21.6 {
    private static void improvedTooltips$changeTooltipColor(GuiGraphicsExtractor instance, RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height) {
    //?} else
    /*private static void improvedTooltips$changeTooltipColor(GuiGraphicsExtractor instance, Function<Identifier, RenderType> renderPipeline, Identifier location, int x, int y, int width, int height) {*/
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
    //?} else {
    /*
    @Inject(method = "renderTooltipBackground", at = @At("HEAD"), cancellable = true)
    private static void improvedTooltips$changeTooltipColor(GuiGraphicsExtractor graphics, int x, int y, int width, int height, int z, CallbackInfo ci) {
        if (!ITConfig.instance().coloredTooltips || !(graphics instanceof ColorExtractor extractor)) return;

        int x0 = x - 3 - 9;
        int y0 = y - 3 - 9;
        int paddedWidth = width + 3 + 3 + 18;
        int paddedHeight = height + 3 + 3 + 18;

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, z);
        ClientTooltipBase.setShaderColor(extractor.improvedTooltips$getTooltipColor());
        graphics.blitSprite(ImprovedTooltips.id("tooltip/background"), x0, y0, paddedWidth, paddedHeight);
        graphics.blitSprite(ImprovedTooltips.id("tooltip/frame"), x0, y0, paddedWidth, paddedHeight);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        graphics.pose().popPose();

        ci.cancel();
    }
    *///?}
}
