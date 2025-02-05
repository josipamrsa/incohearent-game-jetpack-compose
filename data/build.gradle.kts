plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("kotlin-kapt")
}

android {
    namespace = "com.jmrsa.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_SOCKET_URL", "\"ws://192.168.178.42:8080\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_SOCKET_URL", "ws://192.168.178.42:8080/")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))

    // Base
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.multidex)
    implementation(libs.androidx.multidex)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    implementation(libs.retrofit.core)

    // Serialization
    implementation(libs.kotlinx.serialization.json)

    // OkHttp3
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.interceptor)

    // Timber
    implementation(libs.timber)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    testImplementation (libs.hilt.testing)
    androidTestImplementation (libs.hilt.testing)
    kaptTest (libs.hilt.testing)
    kaptAndroidTest(libs.hilt.testing)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)

    // Ktor Client
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.websockets)
    implementation(libs.ktor.logging)
}