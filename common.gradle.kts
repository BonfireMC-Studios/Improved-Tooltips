val modVersion = "1.0.1"
val javaVersion = property("java").toString().toInt()
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

version = "$modVersion+${project.name}"
group = "ua.bonfiremc"
extensions.configure<BasePluginExtension> { archivesName = "improved-tooltips" }

repositories {
    maven("https://maven.isxander.dev/releases")
    maven("https://maven.terraformersmc.com/")
}

extensions.configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.toVersion(javaVersion)
    targetCompatibility = JavaVersion.toVersion(javaVersion)
}

tasks.withType<JavaCompile>().configureEach {
    options.release = javaVersion
}

val resourceProps = mapOf(
    "version" to version.toString(),
    "minecraft_range" to property("minecraft_range").toString(),
    "fabric_loader_version" to libs.findVersion("fabric-loader").get().requiredVersion,
    "java_version" to javaVersion,
    "extra_mixins" to property("extra_mixins").toString().split(",").joinToString { "\"$it\"" },
)

tasks.named<ProcessResources>("processResources") {
    val props = resourceProps

    inputs.properties(props)

    filesMatching(listOf("fabric.mod.json", "improvedtooltips.mixins.json")) {
        expand(props)
    }
}

tasks.named<Jar>("jar") {
    from(rootProject.file("LICENSE"))
}
