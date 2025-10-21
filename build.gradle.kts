// Top-level build file
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.google.services) apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
    jacoco
}

jacoco {
    toolVersion = "0.8.11"
}

subprojects {
    // --- Apply plugins ---
    pluginManager.apply("org.jlleitschuh.gradle.ktlint")
    pluginManager.apply("io.gitlab.arturbosch.detekt")
    pluginManager.apply("jacoco")

    // --- Detekt setup ---
    dependencies {
        "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.8")
    }

    extensions.configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension>("detekt") {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    }

    // --- JaCoCo setup for all test tasks ---
    tasks.matching { it.name in setOf("test", "testDebugUnitTest") }.configureEach {
        extensions.findByType<JacocoTaskExtension>()?.apply {
            isIncludeNoLocationClasses = true
            excludes = listOf("jdk.internal.*")
        }
    }
}

// --- Merged JaCoCo Report ---
tasks.register<JacocoReport>("jacocoMergedReport") {
    group = "verification"
    description = "Generates a merged JaCoCo coverage report for all modules."

    dependsOn(
        subprojects.flatMap { p ->
            listOfNotNull(
                p.tasks.findByName("test"),
                p.tasks.findByName("testDebugUnitTest")
            )
        }
    )

    // Collect coverage execution data
    executionData.setFrom(fileTree(project.rootDir) {
        include(
            "**/build/jacoco/*.exec",
            "**/build/jacoco/*.ec",
            "**/outputs/unit_test_code_coverage/**/test*.exec",
            "**/outputs/unit_test_code_coverage/**/test*.ec"
        )
    })

    // Class & source directories (use layout.buildDirectory)
    val classDirs = files(subprojects.map { p ->
        files(
            // Kotlin (Android)
            p.layout.buildDirectory.dir("tmp/kotlin-classes/debug").get().asFileTree.matching {
                exclude(
                    "**/R.class", "**/R$*.class",
                    "**/BuildConfig.*", "**/Manifest*.*",
                    "**/*Test*.*", "**/*\$inlined\$*"
                )
            },
            // Java (Android)
            p.layout.buildDirectory.dir("intermediates/javac/debug/classes").get().asFileTree.matching {
                exclude(
                    "**/R.class", "**/R$*.class",
                    "**/BuildConfig.*", "**/Manifest*.*",
                    "**/*Test*.*"
                )
            },
            // JVM modules
            p.layout.buildDirectory.dir("classes/java/main").get().asFileTree.matching {
                exclude("**/*Test*.*")
            }
        )
    })

    val srcDirs = files(subprojects.map { p ->
        files("${p.projectDir}/src/main/java", "${p.projectDir}/src/main/kotlin")
    })

    classDirectories.setFrom(classDirs)
    sourceDirectories.setFrom(srcDirs)

    reports {
        xml.required.set(true)
        xml.outputLocation.set(layout.buildDirectory.file("reports/jacoco/merged/merged.xml"))
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/merged/html"))
        csv.required.set(false)
    }
}
