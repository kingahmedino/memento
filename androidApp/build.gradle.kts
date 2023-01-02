plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.app.memento.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.app.memento.android"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    val composeVersion = "1.2.1"
    val hiltVersion = "2.44"
    val navVersion = "2.5.3"
    val accompanistVersion = "0.28.0"

    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    //location services
    implementation("com.google.android.gms:play-services-location:21.0.1")
    //hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    //kotlin datetime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    //material3
    implementation("androidx.compose.material3:material3:1.0.1")
    //accompanist for jetpack compose
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")
    //splash API
    implementation("androidx.core:core-splashscreen:1.0.0")
    //navigation
    implementation("androidx.navigation:navigation-compose:$navVersion")
    //preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}