plugins {
    id("expenseer.android.library")
    id("expenseer.android.compose")
    id("expenseer.kotlin.koin")
}

android {
    namespace = "com.dprog.settings"
    compileSdk {
        version = release(36)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Auth feature requires UI components and user domain logic.
    implementation(project(":core:ui"))
    implementation(project(":domain:user"))
}
