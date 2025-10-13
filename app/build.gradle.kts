plugins {
    // Use the custom convention plugin for Android application modules. This plugin
    // automatically applies the standard Android and Kotlin plugins and configures
    // compileSdk, minSdk, targetSdk and compiler options.
    id("expenseer.android.application")
    // Compose support is still applied explicitly because only the app module
    // uses Jetpack Compose in this project. Keeping it here avoids enabling
    // Compose unnecessarily for other modules.
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.dprog.expenseer"
    // compileSdk, minSdk and targetSdk are configured centrally by the convention plugin.
    defaultConfig {
        applicationId = "com.dprog.expenseer"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    // Compiler options and compose enablement are configured by the convention plugin.
    // Compose is enabled explicitly here via the buildFeatures block.
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Include the feature modules and shared UI layer in the application. These
    // dependencies bring in the screens and business logic defined in the
    // feature and domain layers.
    implementation(project(":core:ui"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:transaction"))
    implementation(project(":feature:analytics"))
}
