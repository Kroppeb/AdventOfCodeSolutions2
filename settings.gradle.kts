rootProject.name = "AdventOfCodeSolutions"

include("today", "util")

pluginManagement {
	plugins {
		kotlin("jvm") version "1.9.21"
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}