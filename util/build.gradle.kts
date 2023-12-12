plugins {
	kotlin("jvm")
}

repositories {
	mavenCentral()
}

dependencies {
	api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC")
}

kotlin {
	jvmToolchain(21)
}