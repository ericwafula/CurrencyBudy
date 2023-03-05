import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

val apiKeyFile = rootProject.file("apikey.properties")
val apiKeyProperties = Properties().apply {
    load(FileInputStream(apiKeyFile))
}

android {
    namespace = "com.ericwathome.currencybuddy"
    compileSdk = ProjectConfig.COMPILE_SDK

    defaultConfig {
        applicationId = "com.ericwathome.currencybuddy"
        minSdk = ProjectConfig.MIN_SDK
        targetSdk = ProjectConfig.TARGET_SDK
        versionCode = ProjectConfig.VERSION_CODE
        versionName = ProjectConfig.VERSION_NAME

        // should correspond to key/value pairs inside the file
        buildConfigField("String", "API_KEY", apiKeyProperties["API_KEY"].toString())

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "${projectDir}/schemas".toString()
                arguments["room.incremental"] = "true"
            }
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfig.KOTLIN_COMPILER_EXTENSION_VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.google.play.core)
    implementation (libs.core.ktx)
    implementation (libs.lifecycle.runtime.ktx)
    implementation (libs.core.activity.compose)
    implementation (libs.ui)
    implementation (libs.ui.tooling.preview)
    implementation (libs.material3)
    testImplementation (libs.junit)
    androidTestImplementation (libs.junit.ext)
    androidTestImplementation (libs.espresso)
    androidTestImplementation (libs.ui.test.junit4)
    debugImplementation (libs.ui.tooling)
    debugImplementation (libs.ui.test.manifest)
    // pager
    implementation (libs.accompanist.pager)
    // navigation
    implementation (libs.navigation.compose)
    // hilt
    implementation (libs.hilt.android)
    implementation (libs.hilt.navigation.compose)
    kapt (libs.hilt.compiler)
    // retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)
    //room
    implementation (libs.room.runtime)
    implementation (libs.room.ktx)
    annotationProcessor (libs.room.compiler)
    kapt (libs.room.compiler)
    // coroutines
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.core)
    // work manager
    implementation (libs.work.runtime.ktx)
    // lifecycle
    implementation (libs.lifecycle.extensions)
    implementation (libs.lifecycle.viewmodel.compose)
    implementation (libs.lifecycle.viewmodel.ktx)

    // splashscreen
    implementation (libs.core.splashscreen)

    // preference datastore
    implementation (libs.datastore.preferences)

    // icons
    implementation (libs.material.icons.extended)

    // lottie animations
    implementation (libs.lottie.compose)

    // timber
    implementation (libs.timber)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}