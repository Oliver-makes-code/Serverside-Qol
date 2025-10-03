import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    java
    alias(libs.plugins.loom) apply false
}

val versions = mapOf(
    "duely" to "1.0.2"
)

subprojects {
    apply(plugin = "fabric-loom")
    version = versions[name]!!
    group = "de.olivermakesco"

    base.archivesName = name
    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom");

    loom.serverOnlyMinecraftJar()

	repositories {
	}

    val mappings: Configuration by configurations.getting
    val minecraft: Configuration by configurations.getting
    val modImplementation: Configuration by configurations.getting

	dependencies {
		minecraft(rootProject.libs.minecraft)
		mappings(loom.officialMojangMappings())
		modImplementation(rootProject.libs.fabric.loader)

		modImplementation(rootProject.libs.fabric.api)
	}

	tasks.processResources {
		inputs.property("version", version)

		filesMatching("fabric.mod.json") {
			expand(mapOf(
                "version" to inputs.properties["version"]
            ))
		}
	}

	tasks.withType<JavaCompile>().configureEach {
		options.release = 21
	}

	java {
		withSourcesJar()

		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}

	tasks.jar {
		inputs.property("archivesName", base.archivesName)

		from("LICENSE") {
			rename { "${it}_${inputs.properties["archivesName"]}"}
		}
	}
}
