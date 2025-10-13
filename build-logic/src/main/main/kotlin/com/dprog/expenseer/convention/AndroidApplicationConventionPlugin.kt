package com.dprog.expenseer.convention

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin for Android application modules.
 *
 * Applying this plugin will:
 *  - Apply the standard Android application and Kotlin Android plugins.
 *  - Set the compileSdk, minSdk and targetSdk versions used across all application modules.
 *  - Configure Java and Kotlin compiler options to target Java 11.
 *
 * Individual modules can override these settings if necessary, but defining them
 * here avoids duplicating boilerplate across multiple modules.
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<ApplicationExtension> {
                compileSdk = 36
                defaultConfig {
                    minSdk = 28
                    targetSdk = 36
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
                // JVM target for Kotlin is configured globally via the Kotlin Gradle plugin;
                // no kotlinOptions block here because it is not available on the AGP DSL.
            }

            // Configure a Java toolchain to ensure that both Java and Kotlin compilers
            // target the same JDK version. Setting the toolchain implicitly sets
            // kotlinOptions.jvmTarget to the same version, resolving JVM-target
            // mismatches between Java and Kotlin compilation tasks【852511485606112†L606-L741】.
            extensions.configure<JavaPluginExtension> {
                toolchain.languageVersion.set(JavaLanguageVersion.of(11))
            }
        }
    }
}