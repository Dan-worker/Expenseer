plugins {
    id("expenseer.android.application")
    id("expenseer.android.compose")
    id("expenseer.kotlin.koin")
    alias(libs.plugins.kotlin.serialization)
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
    implementation(project(":data:auth"))

    // --- Feature modules ---
    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:analytics"))
    implementation(project(":feature:transaction"))
    implementation(project(":feature:settings"))

    // Firebase Auth via BOM
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // Credential Manager + GoogleID
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
}
