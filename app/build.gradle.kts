plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.nuven.estudos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nuven.estudos"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    dependencies {
        // CameraX core library using the camera2 implementation
        val camerax_version = "1.5.0-alpha03"
        // The following line is optional, as the core library is included indirectly by camera-camera2
        implementation("androidx.camera:camera-core:${camerax_version}")
        implementation("androidx.camera:camera-camera2:${camerax_version}")
        // If you want to additionally use the CameraX Lifecycle library
        implementation("androidx.camera:camera-lifecycle:${camerax_version}")
        // If you want to additionally use the CameraX VideoCapture library
        implementation("androidx.camera:camera-video:${camerax_version}")
        // If you want to additionally use the CameraX View class
        implementation("androidx.camera:camera-view:${camerax_version}")
        // If you want to additionally add CameraX ML Kit Vision Integration
        implementation("androidx.camera:camera-mlkit-vision:${camerax_version}")
        // If you want to additionally use the CameraX Extensions library
        implementation("androidx.camera:camera-extensions:${camerax_version}")
    }


    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}