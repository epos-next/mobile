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

    implementation(libs.viewpager)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.splashscreen)

    implementation(libs.nav.fragment)
    implementation(libs.nav.ui)

    implementation(libs.lifecycle.runtime)

    implementation(libs.napier)

    testImplementation(libs.junit)
    testImplementation(libs.mockk.mockk)
    testImplementation(libs.mockk.agent)
    androidTestImplementation(libs.mockk.android)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.testJunit)
    testImplementation(libs.androidx.test.junit)

    implementation(libs.kotlinx.datetime)
}