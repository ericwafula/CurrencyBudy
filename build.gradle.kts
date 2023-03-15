buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.ANDROID_APPLICATION) version "7.4.1" apply false
    id(Plugins.ANDROID_LIBRARY) version "7.4.1" apply false
    id(Plugins.KOTLIN_ANDROID) version "1.8.10" apply false
    id(Plugins.DAGGER_HILT_ANDROID) version "2.45" apply false
    id(Plugins.ANDROID_DYNAMIC_FEATURE) version "7.4.1" apply false
    id(Plugins.KOTLIN_JVM) version "1.8.0" apply false
}