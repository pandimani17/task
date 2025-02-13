plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id ("dagger.hilt.android.plugin")
    id ("kotlin-kapt")
}

android {
    namespace = "com.task.todo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.task.todo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true

    }
    flavorDimensions += "versions"
    productFlavors{

        create("dev"){
            buildConfigField("String", "BASE_URL", "\"https://dummyjson.com\"")

        }
        create("prod"){
            buildConfigField("String", "BASE_URL", "\"https://dummyjson.com\"")
        }


    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation (Deps.core)
    implementation (Deps.ktxLifeCycle)
    implementation (Deps.activityCompose)
    implementation (Deps.uiCompose)
    implementation (Deps.uiPreview)
    implementation (Deps.materialCompose)

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")



    testImplementation (Deps.junit)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("org.mockito:mockito-core:5.5.0")
    testImplementation ("io.mockk:mockk:1.12.0")

    androidTestImplementation (Deps.testJunit)
    androidTestImplementation (Deps.espressoCore)
    androidTestImplementation (Deps.junitCompose)
    debugImplementation (Deps.uiToolingCompose)
    debugImplementation (Deps.testManifest)

    //Dagger-Hilt
    implementation (Deps.hiltAndroid)
    kapt (Deps.hiltCompiler)
    kapt (Deps.hiltAndroidCompiler)
    implementation(Deps.hiltNavigation)

    implementation(platform("androidx.compose:compose-bom:2025.01.00"))


    //compose dependencies
    implementation (Deps.composeViewModel)
    implementation (Deps.composeNavigation)
    implementation (Deps.composeConstraintLayout)

    //coroutines
    implementation (Deps.coroutinesAndroid)
    implementation (Deps.coroutinesCore)


    //coroutine Lifecycle Scopes
    implementation (Deps.lifecycleViewModel)
    implementation (Deps.lifeCycleRunTime)


    //Retrofit
    implementation (Deps.gson)
    implementation (Deps.retrofit)
    implementation (Deps.retrofitConvertorGson)
    implementation (Deps.loggingInterceptor)


    //EncryptedSharedPreference
    implementation (Deps.sharedPreference)

    implementation ("androidx.compose.material3:material3:1.3.1")


    val room_version = "2.6.1"
    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")


}