plugins {
    id("expenseer.android.library")
    id("expenseer.android.compose")
}

android {
    namespace = "com.dprog.home"
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

    // Home feature depends on shared UI and all domain modules to present
    // categories, transactions and user information on the home screen.
    implementation(project(":core:ui"))
    implementation(project(":domain:category"))
    implementation(project(":domain:transaction"))
    implementation(project(":domain:user"))
}
