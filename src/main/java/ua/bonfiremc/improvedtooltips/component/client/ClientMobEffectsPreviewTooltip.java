package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
//? if >=26.2 {
import net.minecraft.client.gui.Hud;
//?} else if >=1.21.5 {
/*import net.minecraft.client.gui.Gui;
*///?}
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
//? if >=1.21.5 {
import net.minecraft.world.item.alchemy.PotionContents;
//?} else
//import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class ClientMobEffectsPreviewTooltip extends ClientTooltipBase {
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
            //? if >=26.2 {
            sprite(graphics, Hud.getMobEffectSprite(effect.getEffect()), x, y + yOffset, 18, 18);
            //?} else if >=1.21.5 {
            /*sprite(graphics, Gui.getMobEffectSprite(effect.getEffect()), x, y + yOffset, 18, 18);
            *///?} else
            //sprite(graphics, Minecraft.getInstance().getMobEffectTextures().get(effect.getEffect()), x, y + yOffset, 18, 18);

            yOffset += 22;
        }
    }

    @Override
    public void extractText(@NonNull GuiGraphicsExtractor graphics, @NonNull Font font, int x, int y) {
        int yOffset = 0;

        for (MobEffectInstance effect : this.effects) {
            text(graphics, font, description(effect).withStyle(effect.getEffect().value().getCategory().getTooltipFormatting()), x + 22, y + yOffset, -1);
            text(graphics, font, MobEffectUtil.formatDuration(effect, this.durationScale, Minecraft.getInstance().level.tickRateManager().tickrate()).copy().withStyle(ChatFormatting.GRAY), x + 22, y + yOffset + font.lineHeight + 1, -1);

            yOffset += 22;
        }
    }

    @Override
    public int getWidth(@NonNull Font font) {
        int textWidth = 0;

        for (MobEffectInstance effect : this.effects) {
            textWidth = Math.max(textWidth, font.width(description(effect)));
            textWidth = Math.max(textWidth, font.width(MobEffectUtil.formatDuration(effect, this.durationScale, Minecraft.getInstance().level.tickRateManager().tickrate())));
        }

        return 22 + textWidth;
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return this.effects.size() * 18 + (this.effects.size() - 1) * 4 + 2;
    }

    private static MutableComponent description(MobEffectInstance effect) {
        //? if >=1.21.5 {
        return PotionContents.getPotionDescription(effect.getEffect(), effect.getAmplifier());
        //?} else {
        /*MutableComponent name = Component.translatable(effect.getDescriptionId());

        return effect.getAmplifier() > 0
            ? Component.translatable("potion.withAmplifier", name, Component.translatable("potion.potency." + effect.getAmplifier()))
            : name;
        *///?}
    }
}
