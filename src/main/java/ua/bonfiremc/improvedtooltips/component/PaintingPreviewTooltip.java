package ua.bonfiremc.improvedtooltips.component;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
//? if >=1.21.11 {
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
//?} else
//import net.minecraft.world.entity.decoration.PaintingVariant;
//? if <1.21.5 {
/*import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.item.component.CustomData;
*///?}

import java.util.Optional;

public record PaintingPreviewTooltip(PaintingVariant variant, Optional<Component> title, Optional<Component> author) implements TooltipComponent {
    public static Optional<TooltipComponent> of(ItemStack stack) {
        return variant(stack).map(variant -> {
            //? if >=1.21.2 {
            return new PaintingPreviewTooltip(variant.value(), variant.value().title(), variant.value().author());
            //?} else {
            /*Optional<Component> title = variant.unwrapKey().map(key -> Component.translatable(key.location().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW));
            Optional<Component> author = variant.unwrapKey().map(key -> Component.translatable(key.location().toLanguageKey("painting", "author")).withStyle(ChatFormatting.GRAY));

            return new PaintingPreviewTooltip(variant.value(), title, author);
            *///?}
        });
    }

    private static Optional<Holder<PaintingVariant>> variant(ItemStack stack) {
        //? if >=1.21.5 {
        return Optional.ofNullable(stack.get(DataComponents.PAINTING_VARIANT));
        //?} else {
        /*CustomData data = stack.get(DataComponents.ENTITY_DATA);
        var level = Minecraft.getInstance().level;

        if (data == null || data.isEmpty() || level == null) {
            return Optional.empty();
        }

        return data.read(level.registryAccess().createSerializationContext(NbtOps.INSTANCE), Painting.VARIANT_MAP_CODEC).result();
        *///?}
    }
}
