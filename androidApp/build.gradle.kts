plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                val decomposeRouter = "0.3.0"
                val decompose = "2.1.0-compose-experimental-alpha-06"
                val essenty = "1.2.0-alpha-05"
                implementation(project(":shared"))
                // You will need to also bring in decompose and essenty
                implementation("com.arkivanov.decompose:decompose:${decompose}")
                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:${decompose}")
                implementation("com.arkivanov.essenty:parcelable:${essenty}")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.myapplication.MyApplication"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
