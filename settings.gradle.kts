pluginManagement {
    repositories {
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

rootProject.name = "LayarTancepXXI"
include(":app")
include(":styling")
include(":core")
include(":shared")
include(":feature:splashscreen")
include(":feature:home")
include(":feature:login")
include(":feature:register")
include(":feature:detailmovie")
include(":feature:player")
