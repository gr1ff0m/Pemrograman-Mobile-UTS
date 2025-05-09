plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.edumate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.edumate"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }

        resourceConfigurations += listOf("en", "id") // Bahasa Indonesia & English
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    dependencies {
        val composeBom = platform("androidx.compose:compose-bom:2024.03.00")
        implementation(composeBom)

        implementation("androidx.core:core-ktx:1.12.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        implementation("androidx.activity:activity-compose:1.9.0")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        implementation("androidx.navigation:navigation-compose:2.8.9")
        implementation("androidx.compose.material3:material3:1.0.0")
        implementation("androidx.compose.material:material-icons-extended:1.5.0")
        implementation("androidx.compose.ui:ui:1.4.0")
        implementation("androidx.compose.material3:material3:1.0.0")
        implementation("androidx.compose.ui:ui-tooling:1.4.0")
        implementation("androidx.compose.foundation:foundation:1.4.0")

        // Untuk animasi
        implementation("androidx.compose.animation:animation")

        // Retrofit untuk API
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.okhttp3:okhttp:4.9.3")

        // Testing
        implementation(libs.androidx.junit.ktx)
        testImplementation(kotlin("test"))
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test:runner:1.5.2")
        androidTestImplementation("androidx.test:core:1.5.0")

        // Preview tools
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
    }
}
dependencies {
    implementation(libs.androidx.junit.ktx)
    testImplementation(kotlin("test"))
}