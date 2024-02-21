object Deps {

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val ktxLifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ktxLifeCycle}"
    const val activityCompose ="androidx.activity:activity-compose:${Versions.activityCompose}"
    const val uiCompose = "androidx.compose.ui:ui${Versions.uiCompose}"
    const val uiPreview = "androidx.compose.ui:ui-tooling-preview${Versions.uiPreview}"
    const val materialCompose = "androidx.compose.material:material${Versions.materialCompose}"
    const val junit ="junit:junit:${Versions.junit}"
    const val testJunit = "androidx.test.ext:junit:${Versions.xJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val junitCompose ="androidx.compose.ui:ui-test-junit4:${Versions.junitCompose}"
    const val uiToolingCompose ="androidx.compose.ui:ui-tooling:${Versions.uiToolingCompose}"
    const val testManifest ="androidx.compose.ui:ui-test-manifest:${Versions.testManifest}"



    //Dagger-Hilt
    const val hiltAndroid ="com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hiltCompiler ="com.google.dagger:hilt-compiler:${Versions.hiltCompiler}"
    const val hiltAndroidCompiler ="com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}"
    const val hiltNavigation ="androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"

    //compose Dependencies
    const val composeViewModel ="androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"

    //coroutines
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"

    //coroutines lifecycle Scopes
    const val lifecycleViewModel ="androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val lifeCycleRunTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRunTime}"

    //Retrofit
    const val gson ="com.google.code.gson:gson:${Versions.gson}"
    const val retrofit ="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConvertorGson ="com.squareup.retrofit2:converter-gson:${Versions.retrofitConvertorGson}"
    const val loggingInterceptor ="com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"


    //EncryptedSharedPreference
    const val sharedPreference ="androidx.security:security-crypto-ktx:${Versions.sharedPreference}"

}

object Versions {

    const val core = "1.12.0"
    const val ktxLifeCycle = "2.6.2"
    const val activityCompose ="1.8.0"
    const val uiCompose =""
    const val uiPreview = ""
    const val materialCompose = ""
    const val junit ="4.13.2"
    const val xJunit = "1.1.5"
    const val espressoCore = "3.5.1"
    const val junitCompose ="1.4.1"
    const val uiToolingCompose =""
    const val testManifest ="1.4.1"

    //Dagger-Hilt
    const val hiltAndroid ="2.45"
    const val hiltCompiler ="2.45"
    const val hiltAndroidCompiler ="2.45"
    const val hiltNavigation ="1.0.0"

    //compose dependencies
    const val composeViewModel ="2.6.1"
    const val composeNavigation = "2.5.3"
    const val composeConstraintLayout = "1.0.1"

    //coroutines
    const val coroutinesAndroid = "1.6.4"
    const val coroutinesCore = "1.6.4"

    //coroutines lifecycle Scopes
    const val lifecycleViewModel ="2.6.1"
    const val lifeCycleRunTime = "2.6.1"

    //Retrofit
    const val gson ="2.8.9"
    const val retrofit ="2.9.0"
    const val retrofitConvertorGson ="2.9.0"
    const val loggingInterceptor ="4.10.0"


    //EncryptedSharedPreference
    const val sharedPreference ="1.1.0-alpha06"

}