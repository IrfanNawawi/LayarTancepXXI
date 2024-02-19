plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "id.heycoding.core"
    compileSdk = AndroidProjectConfig.compileSdk

    defaultConfig {
        minSdk = AndroidProjectConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        buildConfig = true
    }
    flavorDimensions += listOf("default")
    productFlavors {
        create("production") {
            dimension = "default"
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "BASE_URL_IMAGE", "\"https://image.tmdb.org/t/p/\"")
            buildConfigField("String", "BASE_URL_YOUTUBE", "\"https://www.googleapis.com/youtube/v3/\"")
            buildConfigField("String", "AUTH_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4Y2ZiZDY4NDc2MDNhMzk3M2M1MTdlNTc2MDdjMjRjYiIsInN1YiI6IjVhZjQwMTllOTI1MTQxNzkwZjAwNWYyYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KIG2BAaY8F_N7Ha-bqU5kg7AKDX6qf6pjBuYMEd6104\"")
            buildConfigField("String", "APPLICATION_ID", "\"$AndroidProjectConfig.applicationId\"")
        }
    }
}

dependencies {

    api(Libraries.androidxCoreKtx)
    api(Libraries.androidxAppcompat)
    api(Libraries.googleAndroidMaterial)
    api(Libraries.androidxConstraintLayout)
    api(Libraries.swipeRefreshLayout)

    testApi(Libraries.junit)
    androidTestApi(Libraries.androidJunit)
    androidTestApi(Libraries.espressoCore)

    //view model and lifecycle stuffs
    api(Libraries.lifecycleViewModelKtx)
    api(Libraries.lifecycleLiveDataKtx)
    api(Libraries.lifecycleRuntimeKtx)

    api(Libraries.retrofitConverterGson)
    api(Libraries.retrofit2)

    //coroutine
    api(Libraries.coroutineCore)
    api(Libraries.coroutineAndroid)

    //datastore
    api(Libraries.jetpackDatastore)

    //gson
    api(Libraries.gson)

    //koin
    api(Libraries.koinCore)
    api(Libraries.koinAndroid)

    //coil
    api(Libraries.coil)

    //core module
    api(project(":styling"))
}