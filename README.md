# LocaleSync

A Kotlin/Android library for dynamic localization with server-driven updates.
With LocaleSync, you can download translations from a remote server, store them locally, and use them across your app dynamically — without shipping new APKs for every string change.

## ✨ Features

- 🌍 Dynamic localization powered by a remote JSON file.
- 🔄 Automatic updates using Retrofit + WorkManager.
- 📦 Easy integration with Hilt DI.
- ⚡ Context extensions for seamless access to translated strings.
- 🛠 Configurable initialization (custom endpoint + default language).

## 📦 Installation

Add the dependency to your Gradle build file:

```gradle
dependencies {
    implementation("io.github.ayushkumarmaven:localesync:1.0.0")
}
```

## ⚙️ Setup

### 1. Enable Hilt in your project

In your root `build.gradle`:

```gradle
plugins {
    id("com.google.dagger.hilt.android") version "<latest_version>" apply false
}
```

In your app module `build.gradle`:

```gradle
plugins {
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt") // or ksp depending on setup
}

dependencies {
    implementation("com.google.dagger:hilt-android:<latest_version>")
    kapt("com.google.dagger:hilt-android-compiler:<latest_version>")
}
```

### 2. Provide Retrofit + StringManager with Hilt

**Create a Hilt module:**

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://your.api.base.url/") // replace with actual
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}
```

***Optional:***

```kotlin

    private const val DEFAULT_URL = "https://your.api.base.url/translations.json"

    @Provides
    @Singleton
    fun provideStringManager(
        useCase: GetLocalizedStringUseCase,
        repository: LocaleSyncRepository
    ): StringManager {
        return StringManager(useCase, repository).apply {
            initialize(DEFAULT_URL) // initialize once here
        }
    }
```

### 3. Inject StringManager in your Application

```kotlin
@HiltAndroidApp
class MyApp : Application() {

    @Inject
    lateinit var stringManager: StringManager

    override fun onCreate() {
        super.onCreate()

        stringManager.initialize("https://your.api.base.url/translations.json")
        stringManager.setLanguage("en")
    }
}
```

## 🛠 Usage

### 1. Context Extension

You can get translated strings anywhere with:

```kotlin
val title = context.getLocalizedString("app_name")
textView.text = title
```

This replaces the default `getString()` and fetches from the latest downloaded translations.

### 2. StringManager Functions

Initialize with endpoint:

```kotlin
stringManager.initialize("https://your.api.base.url/translations.json")
```

Set active language:

```kotlin
stringManager.setLanguage("en")
```

Get translation by key:

```kotlin
val value = stringManager.getString("welcome_message")
```

Refresh translations manually:

```kotlin
stringManager.fetchAndUpdate()
```

## 🔑 Example

Inside an Activity/Fragment:

```kotlin
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var stringManager: StringManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val welcomeText = getLocalizedString("welcome_message")
        Toast.makeText(this, welcomeText, Toast.LENGTH_LONG).show()
    }
}
```

## 📋 Requirements

- **Min SDK**: 26
- **Kotlin**: 1.9+
- **Jetpack Compose / ViewBinding**: Supported

**Dependencies**:

- Retrofit2
- Gson
- Hilt (DI)
- Coroutines

## 📜 License

Copyright 2025 Ayush Kumar

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
