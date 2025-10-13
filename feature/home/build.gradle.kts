plugins {
    // Apply the Android library convention plugin, which sets up common
    // Android and Kotlin configuration for library modules.
    id("expenseer.android.library")
}

android {
    namespace = "com.dprog.home"
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

    // Home feature depends on shared UI and all domain modules to present
    // categories, transactions and user information on the home screen.
    implementation(project(":core:ui"))
    implementation(project(":domain:category"))
    implementation(project(":domain:transaction"))
    implementation(project(":domain:user"))
}
