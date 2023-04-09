plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
}

android {
    namespace = ProjectConfig.ONBOARDING_DOMAIN_NAMESPACE
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
            excludes += ProjectConfig.EXCLUDE_PACKAGING_OPTIONS
        }
    }
}

dependencies {

    implementation(project(Modules.CORE_DATA))
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation("com.google.firebase:firebase-auth-ktx:21.1.0")
    testImplementation(libs.bundles.local.tests)
    androidTestImplementation(libs.junit.ext)
}