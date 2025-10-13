plugins {
    id("expenseer.android.application")
    id("expenseer.android.compose")
    alias(libs.plugins.google.services)
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Include the feature modules and shared UI layer in the application. These
    // dependencies bring in the screens and business logic defined in the
    // feature and domain layers.
    implementation(project(":core:ui"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:transaction"))
    implementation(project(":feature:analytics"))

    // --- Firebase ---
    implementation(platform(libs.firebase.bom))
    // Add Firebase Auth for user authentication.
    implementation(libs.firebase.auth.ktx)
    // Add Firestore for cloud data sync.
    implementation(libs.firebase.firestore.ktx)
    // Include analytics and crash reporting.
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
}
