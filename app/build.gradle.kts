import java.util.Properties
import java.io.FileInputStream

plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.DAGGER_HILT_ANDROID)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val apiKeyFile = rootProject.file("apikey.properties")
val apiKeyProperties = Properties().apply {
    load(FileInputStream(apiKeyFile))
}

apply(from = "app_version.gradle")

android {
    namespace = ProjectConfig.PROJECT_NAMESPACE
    compileSdk = ProjectConfig.COMPILE_SDK

    val appVersionCode: String by project
    val appVersionName: String by project

    defaultConfig {
        applicationId = ProjectConfig.PROJECT_NAMESPACE
        minSdk = ProjectConfig.MIN_SDK
        targetSdk = ProjectConfig.TARGET_SDK
        versionCode = appVersionCode.toInt()
        versionName = appVersionName

        // should correspond to key/value pairs inside the file
        buildConfigField("String", "API_KEY", apiKeyProperties["API_KEY"].toString())

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "${projectDir}/schemas".toString()
                arguments["room.incremental"] = "true"
            }
        }

        testInstrumentationRunner = ProjectConfig.ANDROID_JUNIT_RUNNER
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
            excludes += "/META-INF/{AL2.0,LGPL2.1,gradle/incremental.annotation.processors}"
        }
    }
}

dependencies {
    implementation(project(Modules.FEATURE_ONBOARDING_PRESENTATION))
    implementation(project(Modules.FEATURE_ONBOARDING_DATA))
    implementation(project(Modules.FEATURE_CONVERTER_DATA))
    implementation(project(Modules.FEATURE_CONVERTER_DOMAIN))
    implementation(project(Modules.FEATURE_CONVERTER_PRESENTATION))
    implementation(project(Modules.CORE_DATA))
    implementation(project(Modules.CORE_PRESENTATION))
    implementation(libs.google.play.core)
    implementation (libs.core.ktx)
    implementation (libs.lifecycle.runtime.ktx)
    implementation (libs.core.activity.compose)
    implementation (libs.jetbrains.annotations)

    implementation (libs.bundles.compose)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase.analytics)
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
    implementation (libs.bundles.hilt)
    kapt (libs.hilt.compiler)
    // coroutines
    implementation (libs.bundles.coroutines)
    // lifecycle
    implementation (libs.bundles.lifecycle)

    // splashscreen
    implementation (libs.core.splashscreen)

    // timber
    implementation (libs.timber)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}