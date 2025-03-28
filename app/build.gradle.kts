plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "asgarov.elchin.plantly"
    compileSdk = 35

    defaultConfig {
        applicationId = "asgarov.elchin.plantly"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"${project.properties["API_KEY"]}\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", "\"${project.properties["API_KEY"]}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose dependencies
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.navigation.compose)
    implementation (libs.google.accompanist.flowlayout)

    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)

    //Dagger - Hilt
    implementation (libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Jetpack Compose Navigation
    implementation(libs.androidx.navigation.compose.v275)

    // KotlinX Serialization (For Type-Safe Navigation)
    implementation(libs.kotlinx.serialization.json)

    // Core Paging 3 Library (for network pagination)
    implementation(libs.androidx.paging.runtime.ktx)

    // Paging 3 Support for Jetpack Compose
    implementation(libs.androidx.paging.compose)

    // Splash Screen
    implementation (libs.androidx.core.splashscreen)

    // Jetpack DataStore (Preferences)
    implementation(libs.androidx.datastore.preferences)

    // Coroutine support for DataStore
    implementation(libs.kotlinx.coroutines.android)

    // Icons
    implementation (libs.androidx.material.icons.extended)

    // Google fonts
    implementation (libs.androidx.ui.text.google.fonts)

    // Moshi for Json parsing
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.converter.moshi)
    ksp(libs.moshi.kotlin.codegen)




}