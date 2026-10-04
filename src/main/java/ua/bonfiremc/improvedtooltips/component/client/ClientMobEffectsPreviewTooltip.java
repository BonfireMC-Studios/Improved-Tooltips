package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.item.alchemy.PotionContents;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ClientMobEffectsPreviewTooltip implements ClientTooltipComponent {
    private final List<MobEffectInstance> effects;
    private final float durationScale;

    public ClientMobEffectsPreviewTooltip(List<MobEffectInstance> effects, float durationScale) {
        this.effects = effects;
        this.durationScale = durationScale;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        int yOffset = 0;

        for (MobEffectInstance effect : this.effects) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Hud.getMobEffectSprite(effect.getEffect()), x, y + yOffset, 18, 18);

            yOffset += 22;
        }
    }

    @Override
    public void extractText(@NonNull GuiGraphicsExtractor graphics, @NonNull Font font, int x, int y) {
        int yOffset = 0;

        for (MobEffectInstance effect : this.effects) {
            graphics.text(font, PotionContents.getPotionDescription(effect.getEffect(), effect.getAmplifier()).withStyle(effect.getEffect().value().getCategory().getTooltipFormatting()), x + 22, y + yOffset, -1);
            graphics.text(font, MobEffectUtil.formatDuration(effect, this.durationScale, Minecraft.getInstance().level.tickRateManager().tickrate()).copy().withStyle(ChatFormatting.GRAY), x + 22, y + yOffset + font.lineHeight + 1, -1);

            yOffset += 22;
        }
    }

    @Override
    public int getWidth(@NonNull Font font) {
        int textWidth = 0;

        for (MobEffectInstance effect : this.effects) {
            textWidth = Math.max(textWidth, font.width(PotionContents.getPotionDescription(effect.getEffect(), effect.getAmplifier())));
            textWidth = Math.max(textWidth, font.width(MobEffectUtil.formatDuration(effect, this.durationScale, Minecraft.getInstance().level.tickRateManager().tickrate())));
        }

        return 22 + textWidth;
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return this.effects.size() * 18 + (this.effects.size() - 1) * 4 + 2;
    }
}
