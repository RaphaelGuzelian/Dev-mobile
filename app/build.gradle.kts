plugins {
    id("com.android.application")
}


android {
    namespace = "com.example.tp_mobdev"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.tp_mobdev"
        minSdk = 21
        targetSdk = 33
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



    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.osmdroid:osmdroid-android:6.1.17")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.room:room-runtime:2.5.0")
    annotationProcessor("androidx.room:room-compiler:2.5.0")


    implementation("androidx.room:room-runtime:2.5.0");
    annotationProcessor("androidx.room:room-compiler:2.5.0");

    // Optionnel - Support des extensions Kotlin et des Coroutines pour Room
    implementation("androidx.room:room-ktx:2.5.0");

    // Optionnel - Support RxJava2 pour Room
    implementation("androidx.room:room-rxjava2:2.5.0");

    // Optionnel - Support RxJava3 pour Room
    implementation("androidx.room:room-rxjava3:2.5.0");

    // Optionnel - Support Guava pour Room, y compris Optional et ListenableFuture
    implementation("androidx.room:room-guava:2.5.0");

    // Optionnel - Utilitaires de test
    testImplementation("androidx.room:room-testing:2.5.0");

    // Optionnel - Intégration avec Paging 3
    implementation("androidx.room:room-paging:2.5.0");

}

configurations {
    implementation {
        exclude("org.jetbrains.kotlin","kotlin-stdlib-jdk8")
    }
}

