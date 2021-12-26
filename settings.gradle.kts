pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Epos_Next"
include(":androidApp")
include(":shared")

enableFeaturePreview("VERSION_CATALOGS")