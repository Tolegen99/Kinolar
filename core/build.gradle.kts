plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")

}

android {
    compileSdk = Version.compileSdk
    buildToolsVersion = Version.buildTools

    defaultConfig {
        minSdk = Version.minSdk
        targetSdk = Version.targetSdk

        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    //AdapterDelegates
    implementation(Dependencies.AdapterDelegates.core)
    implementation(Dependencies.AdapterDelegates.dsl)
}