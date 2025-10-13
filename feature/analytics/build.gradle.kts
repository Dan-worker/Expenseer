plugins {
    // Apply the Android library convention plugin, which sets up common
    // Android and Kotlin configuration for library modules.
    id("expenseer.android.library")
    id("expenseer.android.compose")
}

android {
    namespace = "com.dprog.analytics"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Features depend on the domain layers that provide business logic for
    // categories, transactions and users, as well as shared UI components.
    implementation(project(":core:ui"))
    implementation(project(":domain:category"))
    implementation(project(":domain:transaction"))
    implementation(project(":domain:user"))
}
