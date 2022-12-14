object Versions {
    //Core
    const val kotlin = "1.6.21"
    const val compose = "1.2.0"
    const val compose_paging = "1.0.0-alpha17"
    const val constraint_layout = "1.0.1"
    const val accompanist = "0.25.0"
    const val compose_activity = "1.3.1"
    const val nav_version = "2.5.2"
    const val appCompat = "1.5.1"
    const val material  = "1.6.1"
    const val lifecycle = "2.3.1"
    const val androidx_core = "1.8.0"
    const val hilt = "2.40"
    const val hiltCompose = "1.0.0"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.0"
    const val moshi = "1.11.0"
    const val converterMoshi = "2.9.0"
    const val coroutines = "1.5.2"
    const val coil = "2.2.2"
    const val lottie = "5.0.3"
    const val gson = "2.9.1"
    const val palette = "1.0.0"
    const val icon_extended = "1.4.0-alpha02"
    const val browser = "1.5.0-alpha01"
}

object AppMetaData {
    const val id = "com.android.sample.tmdb"
    const val targetSdkVersion = 33
    const val compileSdkVersion = 33
    const val minSdkVersion = 21
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Deps {
    //Compose
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.compose_activity}"
    const val  composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val  accompanist = "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.nav_version}"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint_layout}"
    const val iconExtended = "androidx.compose.material:material-icons-extended:${Versions.icon_extended}"

    // Paging Compose
    const val composePaging = "androidx.paging:paging-compose:${Versions.compose_paging}"

    //UI
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val palette = "androidx.palette:palette-ktx:${Versions.palette}"
    const val browser = "androidx.browser:browser:${Versions.browser}"

    //Core
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    //Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hilt_compose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompose}"

    //Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.converterMoshi}"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // Moshi
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    //Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    //Coil
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    //Lottie
    const val lottie = "com.airbnb.android:lottie-compose:${Versions.lottie}"

    //Gson
    const val gson =  "com.google.code.gson:gson:${Versions.gson}"
}