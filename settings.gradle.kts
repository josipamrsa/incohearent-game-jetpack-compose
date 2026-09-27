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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "IncohearentGame"

// `-PserverOnly` configures just the server and the protocol, so the server can be built
// on a machine without the Android SDK (e.g. a Docker build or a hosting platform).
val serverOnly = providers.gradleProperty("serverOnly").isPresent

if (!serverOnly) {
    include(":app")
    include(":data")
    include(":domain")
}
include(":protocol")
include(":server")
