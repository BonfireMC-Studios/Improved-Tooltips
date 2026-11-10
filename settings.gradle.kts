pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        maven("https://maven.kikugie.dev/releases")
        gradlePluginPortal()
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.8"
}

stonecutter {
    create(rootProject) {
        fun obfuscated(vararg versions: String) = versions.forEach { version(it).buildscript("build.obf.gradle.kts") }

        obfuscated("1.21.1", "1.21.4", "1.21.10", "1.21.11")
        versions("26.1", "26.2", "26.3")
        vcsVersion = "26.3"
    }
}

rootProject.name = "improved-tooltips"
