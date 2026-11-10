plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "26.3"

stonecutter parameters {
    replacements.string(current.parsed >= "1.21.11") {
        replace("ResourceLocation", "Identifier")
    }
    replacements.string(current.parsed >= "26.3") {
        replace("com.mojang.blaze3d.pipeline.RenderPipeline", "com.mojang.renderpearl.api.pipeline.RenderPipeline")
        replace("com/mojang/blaze3d/pipeline/RenderPipeline", "com/mojang/renderpearl/api/pipeline/RenderPipeline")
    }
    replacements.string(current.parsed >= "26.1") {
        replace("GuiGraphics", "GuiGraphicsExtractor")
    }
}
