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

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Version.RETROFIT_VERSION}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Version.RETROFIT_VERSION}"
    }

    object Coroutine {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINE_VERSION}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINE_VERSION}"
    }
}