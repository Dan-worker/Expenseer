plugins {
    id("expenseer.android.library")
    id("expenseer.kotlin.koin")
}

android {
    namespace = "com.dprog.transaction"
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

    // Depend on core modules for common utilities, database, network,
    // authentication and sync functionality used by the data layer.
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:sync"))
    implementation(project(":core:auth"))

    // Depend on the transaction domain layer so that data implementations can
    // fulfil the domain contracts.
    implementation(project(":domain:transaction"))
}
