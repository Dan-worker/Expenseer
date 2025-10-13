package com.dprog.expenseer

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Convention plugin that enables Jetpack Compose and wires common UI dependencies.
 *
 * When applied to an Android application or library module this plugin will:
 *
 *  * Apply the Kotlin Compose compiler plugin (`org.jetbrains.kotlin.plugin.compose`).
 *  * Enable the Compose build feature on the Android Gradle extension.
 *  * Add a curated set of dependencies via the shared version catalog for Compose UI,
 *    coroutines, lifecycle ViewModel, navigation and Koin. By centralising these
 *    dependencies here, feature modules only need to declare `id("expenseer.android.compose")` to
 *    gain access to a consistent and up‑to‑date UI stack.
 *
 * To use this plugin, add the following to a module's plugins block:
 *
 * ```kotlin
 * plugins {
 *     id("expenseer.android.library")
 *     id("expenseer.android.compose")
 * }
 * ```
 */
class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        // Apply the Compose compiler plugin so Compose code is compiled correctly.
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        // Configure Android application modules.
        plugins.withId("com.android.application") {
            extensions.configure<ApplicationExtension> {
                buildFeatures.compose = true
            }
            addComposeAndAuxiliaryDependencies()
        }

        // Configure Android library modules.
        plugins.withId("com.android.library") {
            extensions.configure<LibraryExtension> {
                buildFeatures.compose = true
            }
            addComposeAndAuxiliaryDependencies()
        }
    }

    /**
     * Adds Compose, navigation, lifecycle, coroutines and Koin dependencies using the version catalog.
     */
    private fun Project.addComposeAndAuxiliaryDependencies() {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        // Compose baseline
        val composeBom = libs.findLibrary("androidx-compose-bom").get()
        val activityCompose = libs.findLibrary("androidx-activity-compose").get()
        val ui = libs.findLibrary("androidx-compose-ui").get()
        val graphics = libs.findLibrary("androidx-compose-ui-graphics").get()
        val preview = libs.findLibrary("androidx-compose-ui-tooling-preview").get()
        val material3 = libs.findLibrary("androidx-compose-material3").get()
        val uiTooling = libs.findLibrary("androidx-compose-ui-tooling").get()
        val uiTestJunit4 = libs.findLibrary("androidx-compose-ui-test-junit4").get()
        val uiTestManifest = libs.findLibrary("androidx-compose-ui-test-manifest").get()
        val junit4 = libs.findLibrary("junit").get()

        // Navigation. These dependencies allow building navigation graphs
        // declaratively within Compose. The versions are defined in the version catalog.
        val nav3Runtime = libs.findLibrary("androidx-navigation3-runtime").get()
        val nav3Ui = libs.findLibrary("androidx-navigation3-ui").get()
        val nav3ViewModel = libs.findLibrary("androidx-lifecycle-viewmodel-navigation3").get()

        // Lifecycle ViewModel and Compose adapters
        val lifecycleViewModelKtx = libs.findLibrary("androidx-lifecycle-viewmodel-ktx").get()
        val lifecycleViewModelCompose = libs.findLibrary("androidx-lifecycle-viewmodel-compose").get()

        // Coroutines
        val coroutinesCore = libs.findLibrary("coroutines-core").get()
        val coroutinesAndroid = libs.findLibrary("coroutines-android").get()

        // Koin DI
        val koinCore = libs.findLibrary("koin-core").get()
        val koinAndroid = libs.findLibrary("koin-android").get()
        val koinCompose = libs.findLibrary("koin-androidx-compose").get()

        dependencies {
            // Compose platform BOM and base UI components
            add("implementation", platform(composeBom))
            add("implementation", activityCompose)
            add("implementation", ui)
            add("implementation", graphics)
            add("implementation", preview)
            add("implementation", material3)

            // Navigation and lifecycle dependencies
            add("implementation", nav3Runtime)
            add("implementation", nav3Ui)
            add("implementation", nav3ViewModel)
            add("implementation", lifecycleViewModelKtx)
            add("implementation", lifecycleViewModelCompose)

            // Coroutines
            add("implementation", coroutinesCore)
            add("implementation", coroutinesAndroid)

            // Koin DI
            add("implementation", koinCore)
            add("implementation", koinAndroid)
            add("implementation", koinCompose)

            // Unit testing
            add("androidTestImplementation", platform(composeBom))
            add("androidTestImplementation", uiTestJunit4)

            // Tooling for preview and inspection
            add("debugImplementation", uiTooling)
            add("debugImplementation", uiTestManifest)

            // Unit test dependencies
            add("testImplementation", junit4)
        }
    }
}
