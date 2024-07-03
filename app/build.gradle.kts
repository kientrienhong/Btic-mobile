plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.room")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.bticapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bticapplication"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "option_name" to "option_value",
                    // other options...
                )
            }
        }
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }

    buildFeatures {
        viewBinding = true
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
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // TODO Define dependency in lib.version.toml
    val room_version = "2.6.1"
    val lifecycle_version = "2.8.2"
    val hilt_version = "2.50"
    val security_version = "1.0.0-alpha02"
    val activity_version = "1.9.0"
    val fragment_version = "1.8.0"
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Dagger
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    //Room
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // Room
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    //Datastore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")

    //Activity + Fragment
    implementation("androidx.activity:activity-ktx:$activity_version")
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    //LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    //Okhttp3 for toMediaType()
    implementation("com.squareup.okhttp3:okhttp:4.7.2")

    // Encrypt
    implementation("androidx.security:security-crypto:$security_version")

    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-storage")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

}