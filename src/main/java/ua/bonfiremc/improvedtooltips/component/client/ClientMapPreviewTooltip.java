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
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.joml.Matrix3x2fStack;
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;

public class ClientMapPreviewTooltip implements ClientTooltipComponent {
    private static final Identifier MAP_BACKGROUND = ImprovedTooltips.id("extended_map_background");

    private final MapId mapId;
    private final MapItemSavedData data;

    public ClientMapPreviewTooltip(MapId mapId) {
        this.mapId = mapId;

        Level level = Minecraft.getInstance().level;

        this.data = level != null
            ? MapItem.getSavedData(mapId, level)
            : null;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        if (data == null) {
            return;
        }

        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, MAP_BACKGROUND, x, y, 140, 140);

        MapRenderState state = new MapRenderState();
        Matrix3x2fStack stack = graphics.pose();

        stack.pushMatrix();
        stack.translate(x + 6, y + 6);

        Minecraft.getInstance().getMapRenderer().extractRenderState(this.mapId, this.data, state);

        graphics.map(state);

        stack.popMatrix();
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return 140;
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return 142;
    }
}
