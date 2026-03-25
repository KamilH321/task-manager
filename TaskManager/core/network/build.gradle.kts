plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.dagger)
}

android {
    namespace = "ru.itis.taskmanager.network"
}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
}