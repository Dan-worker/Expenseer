plugins {
    // Use the Kotlin DSL plugin for writing build logic.
    `kotlin-dsl`
}

// Use the same repositories as the main project to resolve dependencies.
repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

// Define the convention plugins that will be exposed to the main build.
gradlePlugin {
    plugins {
        create("androidApplication") {
            id = "expenseer.android.application"
            implementationClass = "com.dprog.expenseer.AndroidApplicationConventionPlugin"
        }
        create("androidLibrary") {
            id = "expenseer.android.library"
            implementationClass = "com.dprog.expenseer.AndroidLibraryConventionPlugin"
        }
        create("kotlinLibrary") {
            id = "expenseer.kotlin.library"
            implementationClass = "com.dprog.expenseer.KotlinLibraryConventionPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:8.13.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
}
