object Versions {
    // Libs
    val support_lib = "28.0.0"
    val rxJava = "2.2.3"
    val rxAndroid = "2.1.0"
    val cardView = "28.0.0"
    val recyclerView = "28.0.0"
    val retrofit = "2.4.0"

    // Tests
    val junit = "4.12"
    val mockito = "2.23.0"
    val testRunner = "1.0.2"
    val espesso = "3.0.2"
}

object Libs {
    // Core
    val support_annotations = "com.android.support:support-annotations:${Versions.support_lib}"
    val support_appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support_lib}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    // Optional
    val cardView = "com.android.support:cardview-v7:${Versions.cardView}"
    val recyclerView = "com.android.support:recyclerview-v7:${Versions.recyclerView}"
}

object Tests {
    // Unit tests
    val junit = "junit:junit:${Versions.junit}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    val testRunner = "com.android.support.test:runner:${Versions.testRunner}"
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espesso}"
}