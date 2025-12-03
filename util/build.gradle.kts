dependencies {
	api(libs.coroutines)

	testImplementation(kotlin("test"))

	dependencies {
		testCompileOnly(rootProject.libs.aoc.plugin.annotations)
	}
}




sourceSets {
	val test by getting {
		kotlin.srcDirs("test/")
//		resources.srcDirs("resources/")
	}
}

kotlin {
    explicitApiWarning()	
}