plugins {
    id("com.android.application")
    kotlin("android")
}

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
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
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

    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.tooling)
    implementation(libs.compose.navigation)

    implementation(libs.accompanist.navAnimation)

    implementation(libs.viewpager)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.splashscreen)

    implementation(libs.nav.fragment)
    implementation(libs.nav.ui)

    implementation(libs.lifecycle.runtime)

    implementation(libs.napier)

    implementation(libs.kotlinx.datetime)
    implementation(libs.accompanist.flowLayout)
    implementation(libs.accompanist.systemUi)
}