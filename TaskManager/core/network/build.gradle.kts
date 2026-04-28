plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.dagger)
}

android {
    namespace = "ru.itis.taskmanager.network"
}

dependencies {

    implementation(project(path = ":core:build-config:api"))
    implementation(project(path = ":core:domain"))

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
}