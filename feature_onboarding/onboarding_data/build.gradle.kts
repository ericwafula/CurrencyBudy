plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.DAGGER_HILT_ANDROID)
    id("com.google.gms.google-services")
}

android {
    namespace = ProjectConfig.DATA_NAMESPACE
    compileSdk = ProjectConfig.COMPILE_SDK

    defaultConfig {
        minSdk = ProjectConfig.MIN_SDK
        targetSdk = ProjectConfig.TARGET_SDK

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
    implementation(project(Modules.FEATURE_ONBOARDING_DOMAIN))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.datastore.preferences)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(project(mapOf("path" to ":feature_onboarding:onboarding_domain")))
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)
}

kapt {
    correctErrorTypes = true
}