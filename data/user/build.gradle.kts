plugins {
    // Apply the Android library convention plugin, which sets up common
    // Android and Kotlin configuration for library modules.
    id("expenseer.android.library")
}

android {
    namespace = "com.dprog.user"
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

    // Depend on core modules that provide utilities, database, network,
    // authentication and synchronization capabilities for the data layer.
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:sync"))
    implementation(project(":core:auth"))

    // Depend on the user domain layer for implementing repository contracts.
    implementation(project(":domain:user"))
}
