plugins {
    alias(libs.plugins.fabric.loom.remap)
}

apply(from = rootProject.file("common.gradle.kts"))

val mc = "mc" + project.name.replace(".", "")
fun mcVersion(name: String) = versionCatalogs.named("libs").findVersion("$mc-$name").get().requiredVersion

dependencies {
    minecraft("com.mojang:minecraft:${mcVersion("minecraft")}")
    mappings(loom.officialMojangMappings())

    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api) { version { require(mcVersion("fabric-api")) } }

    modImplementation(libs.yacl) { version { require(mcVersion("yacl")) } }
    modImplementation(libs.modmenu) { version { require(mcVersion("modmenu")) } }

    compileOnly(libs.jspecify)
}
