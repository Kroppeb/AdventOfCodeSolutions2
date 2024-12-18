@file:Suppress("UnstableApiUsage")

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


	val compilerPlugin by configurations.registering

	dependencies {
		compileOnly(rootProject.libs.aoc.plugin.annotations)
		compilerPlugin(rootProject.libs.aoc.plugin)
	}


	sourceSets {
		val main by getting {
			kotlin.srcDirs("src/")
			resources.srcDirs("resources/")
		}
	}

	kotlin {
		jvmToolchain(21)
		compilerOptions {
			for (plugin in compilerPlugin.get()) {
				freeCompilerArgs.add("-Xplugin=${plugin.absolutePath}")
			}
			freeCompilerArgs.add("-Xcontext-receivers")
		}
	}
}
