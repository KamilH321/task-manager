plugins {
    alias(libs.plugins.app.android.library)
    alias(libs.plugins.gradle.secrets)
    alias(libs.plugins.app.dagger)
}

var keyValue = ""
val propsFile = File("properties")
if (propsFile.exists()) {
    keyValue = propsFile.readText()
}

android {
    namespace = "ru.itis.taskmanager.buildconfig.impl"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "API_BASE_URL", "\"http://10.0.2.2:8000/\"")
        buildConfigField("String", "API_KEY", "\"$keyValue\"")
    }
}

dependencies {
    implementation(project(path = ":core:build-config:api"))
}