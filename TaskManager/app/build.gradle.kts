plugins {
    alias(libs.plugins.app.android.application)
    alias(libs.plugins.app.compose)
    alias(libs.plugins.app.dagger)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.itis.taskmanager"

    defaultConfig {
        applicationId = "ru.itis.taskmanager"
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(path = ":core:data"))
    implementation(project(path = ":core:domain"))
    implementation(project(path = ":core:network"))
    implementation(project(path = ":core:utils"))
    implementation(project(path = ":core:designsystem"))
    implementation(project(path = ":feature:auth"))
    implementation(project(path = ":feature:profile"))
    implementation(project(path = ":core:build-config:api"))
    implementation(project(path = ":core:build-config:impl"))

    // Navigation 3
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.x.lifecycle.runtime.ktx)
    implementation(libs.x.activity.compose)
    implementation(libs.retrofit)
}