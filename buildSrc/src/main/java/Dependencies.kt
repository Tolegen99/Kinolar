object Dependencies {
    private const val path = "../commonFiles/gradleScript/"
    const val COMMON = "${path}common.gradle.kts"

    object Cicerone {
        const val core = "com.github.terrakok:cicerone:${Version.CICERONE_VERSION}"
    }

    object Dagger {
        const val core = "com.google.dagger:dagger:${Version.DAGGER_VERSION}"
        const val compiler = "com.google.dagger:dagger-compiler:${Version.DAGGER_VERSION}"
    }
}