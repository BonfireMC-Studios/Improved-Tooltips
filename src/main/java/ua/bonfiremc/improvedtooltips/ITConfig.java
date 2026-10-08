package ua.bonfiremc.improvedtooltips;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;

public class ITConfig {
    public static ConfigClassHandler<ITConfig> HANDLER = ConfigClassHandler.createBuilder(ITConfig.class)
        .id(ImprovedTooltips.id("config"))
        .serializer(config -> GsonConfigSerializerBuilder.create(config)
            .setPath(FabricLoader.getInstance().getConfigDir().resolve("improved-tooltips.json"))
            .build()
        )
        .build();

    // SHULKER BOX CONTAINER PREVIEW

    @AutoGen(category = "general", group = "shulker_box")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean shulkerBoxContentPreview = true;

    @AutoGen(category = "general", group = "shulker_box")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean emptyShulkerBoxContent = true;

    @AutoGen(category = "general", group = "shulker_box")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean coloredShulkerBoxContainer = true;

    // FOOD PREVIEW

    @AutoGen(category = "general", group = "food")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean foodPreview = true;

    @AutoGen(category = "general", group = "food")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean saturationPreview = true;

    // MAP PREVIEW

    @AutoGen(category = "general", group = "map")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean mapPreview = true;

    @AutoGen(category = "general", group = "map")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean mapBackground = true;

    // BEES AND HONEY PREVIEW

    @AutoGen(category = "general", group = "bees_and_honey")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean beesPreview = true;

    @AutoGen(category = "general", group = "bees_and_honey")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean honeyPreview = true;

    // MISC

    @AutoGen(category = "general", group = "misc")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean coloredTooltips = true;

    @AutoGen(category = "general", group = "misc")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean mobEffectsPreview = true;

    @AutoGen(category = "general", group = "misc")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean paintingPreview = true;

    public static ITConfig instance() {
        return HANDLER.instance();
    }
}
