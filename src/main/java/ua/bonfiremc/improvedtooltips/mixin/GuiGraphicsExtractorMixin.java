package ua.bonfiremc.improvedtooltips.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.bonfiremc.improvedtooltips.goose.ColorExtractor;

import java.util.List;
import java.util.Optional;

@Mixin(GuiGraphicsExtractor.class)
public abstract class GuiGraphicsExtractorMixin implements ColorExtractor {
    @Unique
    private int color = -1;

    @Inject(method = "setTooltipForNextFrame(Lnet/minecraft/network/chat/Component;II)V", at = @At("HEAD"))
    public void improvedTooltips$getColor(Component component, int x, int y, CallbackInfo ci) {
        this.improvedTooltips$setColor(List.of(component));
    }

    @Inject(method = "setTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;IILnet/minecraft/resources/Identifier;Z)V", at = @At("HEAD"))
    public void improvedTooltips$getColor(Font font, List<Component> texts, Optional<TooltipComponent> optionalImage, int xo, int yo, @Nullable Identifier style, boolean extraSpaceAfterFirstLine, CallbackInfo ci) {
        this.improvedTooltips$setColor(texts);
    }

    @Inject(method = "setTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IILnet/minecraft/resources/Identifier;)V", at = @At("HEAD"))
    public void improvedTooltips$getColor(Font font, Component text, int xo, int yo, Identifier style, CallbackInfo ci) {
        this.improvedTooltips$setColor(List.of(text));
    }

    @Inject(method = "setComponentTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/resources/Identifier;)V", at = @At("HEAD"))
    public void improvedTooltips$getColor(Font font, List<Component> lines, int xo, int yo, @Nullable Identifier style, CallbackInfo ci) {
        this.improvedTooltips$setColor(lines);
    }

    @Inject(method = "componentHoverEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;setTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Ljava/util/List;II)V"))
    public void improvedTooltips$getColor(Font font, Style hoveredStyle, int xMouse, int yMouse, CallbackInfo ci) {
        TextColor color = hoveredStyle.getColor();

        if (color != null) {
            this.color = color.getValue() | 0xFF000000;
        } else {
            this.color = -1;
        }
    }

    @Unique
    private void improvedTooltips$setColor(List<Component> lines) {
        if (!lines.isEmpty()) {
            Component component = lines.getFirst();

            TextColor color = component.getStyle().getColor();

            if ((color == null || color.getValue() == 0xFFFFFF) && component.getSiblings().size() == 1) {
                color = component.getSiblings().getFirst().getStyle().getColor();
            }

            if (color != null) {
                this.color = color.getValue() | 0xFF000000;
                return;
            }
        }

        this.color = -1;
    }

    @Override
    public int improvedTooltips$getTooltipColor() {
        return this.color;
    }
}
