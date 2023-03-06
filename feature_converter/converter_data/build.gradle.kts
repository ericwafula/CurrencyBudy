import java.util.Properties
import java.io.FileInputStream

plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.DAGGER_HILT_ANDROID)
}

val apiKeyFile = rootProject.file("apikey.properties")
val apiKeyProperties = Properties().apply {
    load(FileInputStream(apiKeyFile))
}

android {
    namespace = ProjectConfig.CONVERTER_DATA_NAMESPACE
    compileSdk = ProjectConfig.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectConfig.MIN_SDK
        targetSdk = ProjectConfig.TARGET_SDK

        // should correspond to key/value pairs inside the file
        buildConfigField("String", "API_KEY", apiKeyProperties["API_KEY"].toString())

        testInstrumentationRunner = ProjectConfig.ANDROID_JUNIT_RUNNER
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,gradle/incremental.annotation.processors}"
        }
    }
}

dependencies {

    implementation(project(Modules.FEATURE_CONVERTER_DOMAIN))
    implementation(project(Modules.CORE_DATA))
    implementation(libs.bundles.coroutines)
    implementation(libs.datastore.preferences)
    implementation(libs.bundles.hilt)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    implementation(libs.jetbrains.annotations)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.room)
    annotationProcessor (libs.room.compiler)
    kapt (libs.room.compiler)
    implementation(libs.bundles.retrofit)
}