plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.DAGGER_HILT_ANDROID)
}

android {
    namespace = ProjectConfig.CONVERTER_PRESENTATION_NAMESPACE
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfig.KOTLIN_COMPILER_EXTENSION_VERSION
    }
    packagingOptions {
        resources {
            excludes += ProjectConfig.EXCLUDE_PACKAGING_OPTIONS
        }
    }

}

dependencies {

    implementation(project(Modules.CORE_DATA))
    implementation(project(Modules.CORE_PRESENTATION))
    implementation(project(Modules.FEATURE_CONVERTER_DOMAIN))
    implementation(libs.core.ktx)
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.compose.ui.test)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.coroutines)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
}

kapt {
    correctErrorTypes = true
}