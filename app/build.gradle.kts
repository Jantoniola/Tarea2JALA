plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "dam.pmdm.tarea2jala"
    compileSdk = 34

    defaultConfig {
        applicationId = "dam.pmdm.tarea2jala"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    //Dependencia para la pantalla de Splash
   implementation("androidx.core:core-splashscreen:1.0.1")
    //Dependencias para el RecyclerView y la CardView
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")
    //Dependencias para el controlador de navegación
    implementation("androidx.navigation:navigation-ui:2.8.4")
    implementation("androidx.navigation:navigation-fragment:2.8.3")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}