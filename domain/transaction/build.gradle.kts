plugins {
    id("expenseer.kotlin.library")
    id("expenseer.kotlin.koin")
}

dependencies {
    // Domain:transaction depends on the category domain for cross-domain logic.
    implementation(project(":domain:category"))
}
