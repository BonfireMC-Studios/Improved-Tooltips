plugins {
    alias(libs.plugins.fabric.loom)
}

val modVersion: String = "1.0.0"

version = "$modVersion+${libs.versions.minecraft.get()}"
group = "ua.bonfiremc"

loom {
    accessWidenerPath = file("src/main/resources/improvedtooltips.accesswidener")
}

repositories {
    maven("https://maven.isxander.dev/releases")
    maven("https://maven.terraformersmc.com/")
}

dependencies {
    minecraft(libs.minecraft)

    implementation(libs.fabric.loader)
    implementation(libs.fabric.api)

    implementation(libs.yacl)
    implementation(libs.modmenu)
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

tasks {
    processResources {
        val minecraftVersion = libs.versions.minecraft.get()
        val fabricLoaderVersion = libs.versions.fabric.loader.get()

        inputs.property("version", version)
        inputs.property("minecraft_version", minecraftVersion)
        inputs.property("fabric_loader_version", fabricLoaderVersion)

        filesMatching("fabric.mod.json") {
            expand(
                "version" to version,
                "minecraft_version" to minecraftVersion,
                "fabric_loader_version" to fabricLoaderVersion
            )
        }
    }

    jar {
        from("LICENSE")
    }
}
