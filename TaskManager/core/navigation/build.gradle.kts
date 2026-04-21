plugins {
    alias(libs.plugins.app.android.library)
}

android {
    namespace = "ru.itis.taskmanager.navigation"
}

dependencies {

    // Navigation 3
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.kotlinx.serialization.core)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}