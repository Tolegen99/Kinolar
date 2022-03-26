object Dependencies {
    private const val path = "../commonFiles/gradleScript/"
    const val COMMON = "${path}common.gradle.kts"

    object Lifecycle {
        const val liveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Version.LIFECYCLE_VERSION}"
    }

    object Cicerone {
        const val core = "com.github.terrakok:cicerone:${Version.CICERONE_VERSION}"
    }

    object Dagger {
        const val core = "com.google.dagger:dagger:${Version.DAGGER_VERSION}"
        const val compiler = "com.google.dagger:dagger-compiler:${Version.DAGGER_VERSION}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Version.RETROFIT_VERSION}"
        const val converterMoshi =
            "com.squareup.retrofit2:converter-moshi:${Version.RETROFIT_VERSION}"
    }

    object Moshi {
        const val core = "com.squareup.moshi:moshi-kotlin:${Version.MOSHI_VERSION}"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.MOSHI_VERSION}"
    }

    object Okhttp {
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP_VERSION}"
    }

    object Coroutine {
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINE_VERSION}"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINE_VERSION}"
    }

    object AdapterDelegates {
        const val core = "com.hannesdorfmann:adapterdelegates4:${Version.ADAPTER_DELEGATE_VERSION}"
        const val dsl = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${Version.ADAPTER_DELEGATE_VERSION}"
    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Version.GLIDE_VERSION}"
        const val compiler = "com.github.bumptech.glide:compiler:${Version.GLIDE_VERSION}"
    }

    object Carbon {
        const val core = "tk.zielony:carbon:${Version.CARBON_VERSION}"
    }

}