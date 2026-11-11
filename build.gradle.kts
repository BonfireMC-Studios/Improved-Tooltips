plugins {
    alias(libs.plugins.fabric.loom)
}

apply(from = rootProject.file("common.gradle.kts"))

val mc = "mc" + project.name.replace(".", "")
fun mcVersion(name: String) = versionCatalogs.named("libs").findVersion("$mc-$name").get().requiredVersion

dependencies {
    minecraft("net.minecraft:minecraft:${mcVersion("minecraft")}")

    implementation(libs.fabric.loader)
    implementation(libs.fabric.api) { version { require(mcVersion("fabric-api")) } }

    implementation(libs.yacl) { version { require(mcVersion("yacl")) } }
    implementation(libs.modmenu) { version { require(mcVersion("modmenu")) } }
}
