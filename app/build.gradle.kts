plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

val apiUrlDev = "https://api.themoviedb.org/3"

android {
    compileSdk = Version.compileSdk
    buildToolsVersion = Version.buildTools

    defaultConfig {
        applicationId = "kz.tolegen.kinolar"
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val API_ENDPOINT_FIELD = "API_ENDPOINT"
        val API_KEY_FIELD = "API_KEY"

        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", API_ENDPOINT_FIELD, Constants.BASE_URL)
            buildConfigField("String", API_KEY_FIELD, Constants.API_KEY)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    implementation(project(Module.CORE))

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    //Cicerone
    implementation(Dependencies.Cicerone.core)

    //Dagger2
    implementation(Dependencies.Dagger.core)
    kapt(Dependencies.Dagger.compiler)

    //Retrofit
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.converterMoshi)

    //Coroutine
    implementation(Dependencies.Coroutine.coroutinesCore)
    implementation(Dependencies.Coroutine.coroutinesAndroid)

    //Okhttp
    implementation(Dependencies.Okhttp.loggingInterceptor)

    //Lifecycle
    implementation(Dependencies.Lifecycle.liveData)

    //Moshi
    implementation(Dependencies.Moshi.core)
    implementation(Dependencies.Moshi.codegen)
}