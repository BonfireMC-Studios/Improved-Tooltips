package ua.bonfiremc.improvedtooltips.component.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
//? if >=1.21.2 {
import net.minecraft.client.renderer.state.MapRenderState;
//?}
//? if <1.21.6 {
/*import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
*///?}
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
//? if >=1.21.6 {
import org.joml.Matrix3x2fStack;
//?}
import org.jspecify.annotations.NonNull;
import ua.bonfiremc.improvedtooltips.ITConfig;
import ua.bonfiremc.improvedtooltips.ImprovedTooltips;

public class ClientMapPreviewTooltip extends ClientTooltipBase {
    private static final Identifier MAP_BACKGROUND = ImprovedTooltips.id("extended_map_background");

    private final MapId id;
    private final MapItemSavedData data;

    private final boolean renderBackground = ITConfig.instance().mapBackground;

    public ClientMapPreviewTooltip(MapId id, MapItemSavedData data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public void extractImage(@NonNull Font font, int x, int y, int w, int h, @NonNull GuiGraphicsExtractor graphics) {
        if (this.renderBackground) {
            sprite(graphics, MAP_BACKGROUND, x, y, 140, 140);
        }

        int padding = this.renderBackground ? 6 : 0;

        //? if >=1.21.6 {
        MapRenderState state = new MapRenderState();
        Matrix3x2fStack stack = graphics.pose();

        stack.pushMatrix();
        stack.translate(x + padding, y + padding);

        Minecraft.getInstance().getMapRenderer().extractRenderState(this.id, this.data, state);

        //? if >=26.1 {
        graphics.map(state);
        //?} else
        //graphics.submitMapRenderState(state);

        stack.popMatrix();
        //?} else {
        /*PoseStack stack = graphics.pose();

        stack.pushPose();
        stack.translate(x + padding, y + padding, 1);
        // The map renderer is built for world space, where decorations sit at negative z.
        stack.scale(1, 1, -1);

        //? if >=1.21.2 {
        MapRenderState state = new MapRenderState();
        Minecraft.getInstance().getMapRenderer().extractRenderState(this.id, this.data, state);
        graphics.drawSpecial(buffers -> Minecraft.getInstance().getMapRenderer().render(state, stack, buffers, true, LightTexture.FULL_BRIGHT));
        //?} else {
        /^Minecraft.getInstance().gameRenderer.getMapRenderer().render(stack, graphics.bufferSource(), this.id, this.data, true, LightTexture.FULL_BRIGHT);
        graphics.flush();
        ^///?}

        stack.popPose();
        *///?}
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
