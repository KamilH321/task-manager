import java.util.Properties

plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.gradle.secrets)
    alias(libs.plugins.app.dagger)
}

android {
    namespace = "ru.itis.taskmanager.buildconfig.impl"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {

        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localProperties.load(localPropertiesFile.inputStream())
        }

        val keyValue = localProperties.getProperty("apiKey") ?: ""

        buildConfigField("String", "API_BASE_URL", "\"http://10.0.2.2:8000/\"")
        buildConfigField("String", "API_KEY", "\"$keyValue\"")
    }
}

dependencies {
    implementation(project(path = ":core:build-config:api"))
}