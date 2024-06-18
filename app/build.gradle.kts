plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.diaryapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.diaryapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
    buildFeatures {
        compose = true
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
        // Enable support for the new language APIs
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"

    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity:1.8.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Zalo Pay SDK
    implementation(files("D:\\Study\\VKU\\2thCode\\Mobile\\ExternalLib\\ZaloAppToApp\\zpdk-release-v3.1.aar"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("androidx.compose.ui:ui:1.6.5")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.1")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.0.0-alpha05")
    implementation("androidx.compose.ui:ui-tooling:1.6.5")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("com.google.accompanist:accompanist-themeadapter-material3:0.28.0")

    //firebase sependecy
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-storage")

    //compose navgation dependency
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.2")

    //Calendar
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    implementation ("com.kizitonwose.calendar:compose:2.5.0")

    //coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    //.env
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")

    //Http
    //implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.6.0")

    // Retrofit with Scalar Converter
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    //encrypt
    implementation("commons-codec:commons-codec:1.14")
}