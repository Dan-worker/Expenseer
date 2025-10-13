package com.dprog.expenseer

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Convention plugin that adds Koin and coroutines support to Kotlin and Android modules.
 *
 * When applied, this plugin adds `koin-core` and `kotlinx-coroutines-core` to every module,
 * and additionally adds `kotlinx-coroutines-android` for Android modules. It can be used
 * on both pure Kotlin JVM modules (e.g. domain modules) and Android modules. For Android
 * modules using Jetpack Compose, the `expenseer.android.compose` plugin already pulls in
 * Koin and coroutines dependencies, so applying both plugins will have no adverse effect.
 */
class KotlinKoinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        val koinCore = libs.findLibrary("koin-core").get()
        val coroutinesCore = libs.findLibrary("coroutines-core").get()
        val coroutinesAndroid = libs.findLibrary("coroutines-android").get()

        // Always add Koin and coroutines-core to any module.
        dependencies {
            add("implementation", koinCore)
            add("implementation", coroutinesCore)
        }

        // For Android modules also add coroutines-android.
        plugins.withId("com.android.application") {
            dependencies {
                add("implementation", coroutinesAndroid)
            }
        }
        plugins.withId("com.android.library") {
            dependencies {
                add("implementation", coroutinesAndroid)
            }
        }
    }
}
