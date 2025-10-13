plugins {
    id("expenseer.android.library")
    id("expenseer.android.compose")
    id("expenseer.kotlin.koin")
}

android {
    namespace = "com.dprog.mylibrary"
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

    // Transaction feature depends on shared UI, the transaction domain and
    // category domain to present and manage transactions with their
    // associated categories.
    implementation(project(":core:ui"))
    implementation(project(":domain:transaction"))
    implementation(project(":domain:category"))
}
