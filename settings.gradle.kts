// Archivo: C:\Users\mayro\Downloads\fitlife_complete\settings.gradle.kts

pluginManagement {
    repositories {
        // Añadidos para que Gradle encuentre los plugins como 'com.android.application'
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "fitlife_complete"
include(":app")

