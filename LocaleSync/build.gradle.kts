plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") // Add this line
    id("com.google.dagger.hilt.android")
    id("com.vanniktech.maven.publish") version "0.34.0"
}

android {
    namespace = "io.github.ayushkumar.localSync"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    kotlin {
        jvmToolchain(17)
    }
}


mavenPublishing {
    coordinates("io.github.ayushkumarmaven", "localesync", "1.0.0")

    pom {
        name.set("LocaleSync")
        description.set("A Kotlin/Android library for dynamic localization with server-driven updates.")
        inceptionYear.set("2025")
        url.set("https://github.com/AyushKumar-Codes/LocaleSync")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("AyushKumar-Codes")
                name.set("Ayush Kumar")
                url.set("https://github.com/AyushKumar-Codes")
            }
        }
        scm {
            url.set("https://github.com/AyushKumar-Codes/LocaleSync")
            connection.set("scm:git:git://github.com/AyushKumar-Codes/LocaleSync.git")
            developerConnection.set("scm:git:ssh://git@github.com/AyushKumar-Codes/LocaleSync.git")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.kotlin.stdlib)


    // Retrofit + Gson
    implementation (libs.retrofit)
    implementation (libs.converter.gson)


    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Coroutines (for suspend functions)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)


}