plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "epos_next.app.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 5
        versionName = "1.0.1"
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

    implementation(libs.kotlinx.datetime)
    implementation(libs.accompanist.flowLayout)
    implementation(libs.accompanist.systemUi)

    implementation(libs.kermit.log)

    implementation(platform("com.google.firebase:firebase-bom:29.2.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation("com.google.android.play:core:1.10.3")
    implementation("com.google.android.play:core-ktx:1.8.1")

    implementation("com.google.code.gson:gson:2.9.0")
}