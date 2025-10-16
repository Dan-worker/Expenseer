plugins {
    id("expenseer.android.application")
    id("expenseer.android.compose")
    id("expenseer.kotlin.koin")
    alias(libs.plugins.kotlin.serialization)
//    alias(libs.plugins.google.services)
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

    // --- Core modules ---
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:auth"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:sync"))

    // --- Domain modules ---
    implementation(project(":domain:user"))
    implementation(project(":domain:transaction"))
    implementation(project(":domain:category"))

    // --- Data modules ---
    implementation(project(":data:user"))
    implementation(project(":data:transaction"))
    implementation(project(":data:category"))

    // --- Feature modules ---
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:analytics"))
    implementation(project(":feature:transaction"))

    /*    // --- Firebase ---
        implementation(platform(libs.firebase.bom))
        // Add Firebase Auth for user authentication.
        implementation(libs.firebase.auth.ktx)
        // Add Firestore for cloud data sync.
        implementation(libs.firebase.firestore.ktx)
        // Include analytics and crash reporting.
        implementation(libs.firebase.analytics.ktx)
        implementation(libs.firebase.crashlytics.ktx)*/
}
