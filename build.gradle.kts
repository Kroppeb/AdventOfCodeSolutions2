plugins {
	alias(libs.plugins.kotlin)
}

subprojects {
	apply(plugin = rootProject.libs.plugins.kotlin.get().pluginId)
}

allprojects {
	repositories {
		mavenCentral()
	}

//	dependencies {
//		implementation(libs.annotations)
//	}

	sourceSets {
		val main by getting {
			kotlin.srcDirs("src/")
			resources.srcDirs("resources/")
		}
	}

	kotlin {
		jvmToolchain(21)
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += listOf(
				"-Xcontext-receivers",
			)
		}
	}
}
