package com.dprog.expenseer

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Convention plugin that configures Room with Kotlin Symbol Processing (KSP) for Android modules.
 *
 * Applying this plugin will:
 *
 *  * Apply the KSP Gradle plugin (`com.google.devtools.ksp`) required for Room annotation processing.
 *  * Add Room runtime and Kotlin extensions as implementation dependencies.
 *  * Add Room compiler as a KSP dependency.
 *
 * This plugin can be applied alongside `expenseer.android.library` or
 * `expenseer.android.application`. It has no effect on pure Kotlin JVM modules.
 */
class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        // Apply the KSP plugin to enable annotation processing.
        pluginManager.apply("com.google.devtools.ksp")

        // Configure for application modules
        plugins.withId("com.android.application") {
            configureRoomDependencies()
        }
        // Configure for library modules
        plugins.withId("com.android.library") {
            configureRoomDependencies()
        }
    }

    /**
     * Adds Room dependencies via the version catalog. Uses implementation for runtime
     * and Kotlin extensions, and ksp for the compiler.
     */
    private fun Project.configureRoomDependencies() {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        val roomRuntime = libs.findLibrary("androidx-room-runtime").get()
        val roomKtx = libs.findLibrary("androidx-room-ktx").get()
        val roomCompiler = libs.findLibrary("androidx-room-compiler").get()

        dependencies {
            add("implementation", roomRuntime)
            add("implementation", roomKtx)
            // Use the 'ksp' configuration for Room compiler. This will cause KSP to run the
            // annotation processor and generate code at compile time.
            add("ksp", roomCompiler)
        }
    }
}
