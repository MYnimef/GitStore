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

rootProject.name = "GitStore"
include(":app")
include(":domain")
include(":data-remote")
include(":data-local")

// core
include(":core:navigation:api")
include(":core:navigation:impl")

include(":core:events:api")
include(":core:events:impl")

// common
include(":common:uikit")

// features
include(":feature:main:api")
include(":feature:main:impl")

include(":feature:github")
include(":feature:gitlab")
