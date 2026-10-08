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
import ua.bonfiremc.improvedtooltips.ITConfig;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;

public class ClientMapPreviewTooltip implements ClientTooltipComponent {
    private static final Identifier MAP_BACKGROUND = ImprovedTooltips.id("extended_map_background");

    private final MapId id;
    private final MapItemSavedData data;

    private final boolean renderBackground = ITConfig.instance().mapBackground;

    public ClientMapPreviewTooltip(MapId id) {
        this.id = id;

        Level level = Minecraft.getInstance().level;

        this.data = level != null
            ? MapItem.getSavedData(id, level)
            : null;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        if (data == null) {
            return;
        }

        if (this.renderBackground) {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, MAP_BACKGROUND, x, y, 140, 140);
        }

        MapRenderState state = new MapRenderState();
        Matrix3x2fStack stack = graphics.pose();

        int padding = this.renderBackground ? 6 : 0;

        stack.pushMatrix();
        stack.translate(x + padding, y + padding);

        Minecraft.getInstance().getMapRenderer().extractRenderState(this.id, this.data, state);

        graphics.map(state);

        stack.popMatrix();
    }

    @Override
    public int getWidth(@NonNull Font font) {
        return this.renderBackground ? 140 : 128;
    }

    @Override
    public int getHeight(@NonNull Font font) {
        return this.renderBackground ? 142 : 130;
    }
}
