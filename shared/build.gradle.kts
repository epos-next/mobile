plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.5.31"
    id("com.squareup.sqldelight")
}

version = "1.0"

android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()// sure all ios dependencies support this target

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = false
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.multiplatformSettings.core)
                implementation(libs.multiplatformSettings.serialization)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.datetime)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.auth)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.logging)
                implementation(libs.sqlDelight.runtime)
                implementation(libs.sqlDelight.coroutinesExt)
                implementation(libs.kermit.log)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.kermit.crashlytics)
                implementation(libs.ktor.client.android)
                implementation(libs.sqlDelight.android)
                implementation(libs.sqlDelight.coroutinesExt)
                implementation(libs.compose.ui)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.client.ios)
                implementation(libs.sqlDelight.native)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "epos_next.db"
        verifyMigrations = true
        schemaOutputDirectory = file("epos_next.app.shared.sqldelight.epos_next.app.data")
        migrationOutputDirectory = file("epos_next.app.shared.sqldelight.epos_next.app.data")
        deriveSchemaFromMigrations = true
    }
}
