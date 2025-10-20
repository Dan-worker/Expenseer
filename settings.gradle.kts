pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Expenseer"
includeBuild("build-logic")
include(":app")
include(":core:ui")
include(":core:common")
include(":feature:auth")
include(":feature:home")
include(":domain:transaction")
include(":domain:category")
include(":domain:user")
include(":data:transaction")
include(":data:category")
include(":data:user")
include(":feature:transaction")
include(":feature:analytics")
include(":core:database")
include(":core:network")
include(":core:auth")
include(":core:sync")
include(":feature:settings")
