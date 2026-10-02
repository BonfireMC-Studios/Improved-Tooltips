package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.state.MapRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.joml.Matrix3x2fStack;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.component.MapPreviewTooltip;

public record ClientMapPreviewTooltip(MapPreviewTooltip tooltip) implements ClientTooltipComponent {
    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath("improvedtooltips", "extended_map_background"), x, y, 140, 140);

        MapRenderState state = new MapRenderState();
        Level level = Minecraft.getInstance().level;

        if (level == null) {
            return;
        }

        MapItemSavedData data = MapItem.getSavedData(this.tooltip().mapId(), Minecraft.getInstance().level);

        if (data == null) {
            return;
        }

        Matrix3x2fStack stack = graphics.pose();

        stack.pushMatrix();
        stack.translate(x + 6, y + 6);

        Minecraft.getInstance().getMapRenderer().extractRenderState(this.tooltip.mapId(), data, state);

        graphics.map(state);

        stack.popMatrix();
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return 144;
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return 140;
    }
}
