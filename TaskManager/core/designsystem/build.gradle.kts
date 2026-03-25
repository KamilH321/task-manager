plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.app.compose)
}

android {
    namespace = "ru.itis.taskmanager.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}