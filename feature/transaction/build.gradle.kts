plugins {
    // Apply the Android library convention plugin, which sets up common
    // Android and Kotlin configuration for library modules.
    id("expenseer.android.library")
}

android {
    namespace = "com.dprog.mylibrary"
    // compileSdk and minSdk are provided by the convention plugin.
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    // Java and Kotlin compiler options are configured by the convention plugin.
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Transaction feature depends on shared UI, the transaction domain and
    // category domain to present and manage transactions with their
    // associated categories.
    implementation(project(":core:ui"))
    implementation(project(":domain:transaction"))
    implementation(project(":domain:category"))
}
