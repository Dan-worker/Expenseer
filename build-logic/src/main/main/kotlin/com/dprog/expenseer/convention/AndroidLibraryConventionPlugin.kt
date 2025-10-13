package com.dprog.expenseer.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin for Android library modules.
 *
 * Applying this plugin will:
 *  - Apply the Android library and Kotlin Android plugins.
 *  - Set the compileSdk and minSdk versions used across all library modules.
 *  - Configure Java and Kotlin compiler options to target Java 11.
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                compileSdk = 36
                defaultConfig {
                    minSdk = 28
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
                // Kotlin JVM target is configured globally via the Kotlin Gradle plugin.
                // The Android Gradle DSL no longer exposes a kotlinOptions block on LibraryExtension,
                // so don't attempt to configure jvmTarget here. See KotlinLibraryConventionPlugin
                // for JVM modules.
            }

            // Use a Java toolchain to align Java and Kotlin compilation targets.
            extensions.configure<JavaPluginExtension> {
                toolchain.languageVersion.set(JavaLanguageVersion.of(11))
            }
        }
    }
}