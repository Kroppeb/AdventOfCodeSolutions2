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

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions{
			jvmTarget = "21"
			freeCompilerArgs += listOf(
				"-Xcontext-receivers",
			)
		}
	}
}
