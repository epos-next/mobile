plugins {
    id("com.android.application")
    kotlin("android")
}

val composeVersion = "1.1.0-rc01"

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "epos_next.app.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.google.material)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)

    // Jetpack compose
    implementation(libs.compose.ui)
    implementation(libs.compose.activity)
    implementation(libs.compose.material)
    implementation(libs.compose.tooling)

    // View pager
    implementation(libs.viewpager)
}