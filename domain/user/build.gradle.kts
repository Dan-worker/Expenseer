plugins {
    // Apply the convention plugin for pure Kotlin/JVM modules. This plugin
    // applies the java-library and Kotlin JVM plugins and configures
    // compile options to use Java 11.
    id("expenseer.kotlin.library")
}
// Java and Kotlin compiler options are configured by the convention plugin.
