object Versions {
    // Android
    val compileSdkVersion = 28
    val minSdkVersion = 15
    val targetSdkVersion = 28
    val versionCode = 2
    val versionName = "Broughton"

    // Libs
    val support_lib = "1.0.0"
    val rxJava = "2.2.3"
    val rxAndroid = "2.1.0"
    val rxJavaGsonConverter = "2.4.0"
    val cardView = "1.0.0"
    val recyclerView = "1.0.0"
    val retrofit = "2.4.0"
    val liveData = "2.0.0"
    val room = "2.1.0-alpha03"
    val viewModel = "1.1.1"
    val lifeCycleExtensions = "1.1.1"
    val dagger = "2.20"
    val gson = "2.8.5"
    val picasso = "2.71828"
    val kotlin = "1.2.71"
    val timber = "4.7.1"

    // Tests
    val junit = "4.12"
    val mockito = "2.23.0"
    val testRunner = "1.1.0"
    val espesso = "3.0.2"

    // Others
    val java = "8"
    val androidJavaVersion = 1.8
    val gradle = "3.2.1"
    val gradleKotlin = "1.2.71"
}

object Libs {
    // Core
    val app_compat = "androidx.appcompat:appcompat:${Versions.support_lib}"
    val android_material = "com.google.android.material:material:${Versions.support_lib}"
    val kotlin_standard_lib_jdk_8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val rxjava_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.rxJavaGsonConverter}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val dagger_kotlin = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    // Others
    val gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle}"
    val gradle_kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.gradleKotlin}"

    // Optional
    val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.liveData}"
    val room = "androidx.room:room-runtime:${Versions.room}"
    val roomRxJava = "androidx.room:room-rxjava2:${Versions.room}"
    val roomGuava = "androidx.room:room-guava:${Versions.room}"
    val viewModel = "android.arch.lifecycle:viewmodel:${Versions.viewModel}"
    val lifeCycleExtension = "android.arch.lifecycle:extensions:${Versions.lifeCycleExtensions}"
}

object AnnotationProcessor {
    val support = "com.android.support:support-annotations:${Versions.support_lib}"
    val liveData = "androidx.lifecycle:lifecycle-compiler:${Versions.liveData}"
    val room = "androidx.room:room-compiler:${Versions.room}"
    val dagger = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Tests {
    // Unit tests
    val junit = "junit:junit:${Versions.junit}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espesso}"
}