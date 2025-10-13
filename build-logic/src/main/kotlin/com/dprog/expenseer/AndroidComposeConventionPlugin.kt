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
 * Enables Compose and adds a consistent Compose dependency baseline.
 * Apply with: id("expenseer.android.compose")
 */
class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        plugins.withId("com.android.application") {
            extensions.configure<ApplicationExtension> {
                buildFeatures.compose = true
            }
            addComposeAndNav3Deps()
        }

        plugins.withId("com.android.library") {
            extensions.configure<LibraryExtension> {
                buildFeatures.compose = true
            }
            addComposeAndNav3Deps()
        }
    }

    private fun Project.addComposeAndNav3Deps() {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        // --- Compose baseline ---
        val composeBom = libs.findLibrary("androidx-compose-bom").get()
        val activityCompose = libs.findLibrary("androidx-activity-compose").get()
        val ui = libs.findLibrary("androidx-compose-ui").get()
        val graphics = libs.findLibrary("androidx-compose-ui-graphics").get()
        val preview = libs.findLibrary("androidx-compose-ui-tooling-preview").get()
        val material3 = libs.findLibrary("androidx-compose-material3").get()
        val uiTooling = libs.findLibrary("androidx-compose-ui-tooling").get()
        val uiTestJunit4 = libs.findLibrary("androidx-compose-ui-test-junit4").get()
        val uiTestManifest = libs.findLibrary("androidx-compose-ui-test-manifest").get()

        // --- Navigation3 ---
        val nav3Runtime = libs.findLibrary("androidx-navigation3-runtime").get()
        val nav3Ui = libs.findLibrary("androidx-navigation3-ui").get()
        val vmNav3 = libs.findLibrary("androidx-lifecycle-viewmodel-navigation3").get()

        dependencies {
            // Compose
            add("implementation", platform(composeBom))
            add("implementation", activityCompose)
            add("implementation", ui)
            add("implementation", graphics)
            add("implementation", preview)
            add("implementation", material3)

            add("androidTestImplementation", platform(composeBom))
            add("androidTestImplementation", uiTestJunit4)

            add("debugImplementation", uiTooling)
            add("debugImplementation", uiTestManifest)

            // Navigation3
            add("implementation", vmNav3)
            add("implementation", nav3Runtime)
            add("implementation", nav3Ui)
        }
    }
}
