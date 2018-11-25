object Versions {
    // Android
    val compileSdkVersion = 28
    val minSdkVersion = 15
    val targetSdkVersion = 28
    val versionCode = 1
    val versionName = "1.0"

    // Libs
    val support_lib = "28.0.0"
    val rxJava = "2.2.3"
    val rxAndroid = "2.1.0"
    val rxJavaGsonConverter = "2.4.0"
    val cardView = "28.0.0"
    val recyclerView = "28.0.0"
    val retrofit = "2.4.0"
    val liveData = "1.1.1"
    val room = "1.1.1"
    val viewModel = "1.1.1"
    val lifeCycleExtensions = "1.1.1"
    val dagger = "2.19"
    val gson = "2.8.5"
    val picasso = "2.71828"

    // Tests
    val junit = "4.12"
    val mockito = "2.23.0"
    val testRunner = "1.0.2"
    val espesso = "3.0.2"

    // Others
    val java = "8"
    val androidJavaVersion = 1.8
    val gradle = "3.2.0"
}

object Libs {
    // Core
    val support_appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support_lib}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val rxjava_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.rxJavaGsonConverter}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    // Others
    val gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle}"

    // Optional
    val cardView = "com.android.support:cardview-v7:${Versions.cardView}"
    val recyclerView = "com.android.support:recyclerview-v7:${Versions.recyclerView}"
    val liveData = "android.arch.lifecycle:livedata:${Versions.liveData}"
    val room = "android.arch.persistence.room:runtime:${Versions.room}"
    val roomRxJava = "android.arch.persistence.room:rxjava2:${Versions.room}"
    val roomGuava = "android.arch.persistence.room:guava:${Versions.room}"
    val viewModel = "android.arch.lifecycle:viewmodel:${Versions.viewModel}"
    val lifeCycleExtension = "android.arch.lifecycle:extensions:${Versions.lifeCycleExtensions}"
}

object AnnotationProcessor {
    val support = "com.android.support:support-annotations:${Versions.support_lib}"
    val liveData = "android.arch.lifecycle:compiler:${Versions.liveData}"
    val room = "android.arch.persistence.room:compiler:${Versions.room}"
    val dagger = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Tests {
    // Unit tests
    val junit = "junit:junit:${Versions.junit}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    val testRunner = "com.android.support.test:runner:${Versions.testRunner}"
    val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espesso}"
}