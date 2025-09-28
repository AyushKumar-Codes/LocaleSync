package io.github.ayushkumar.localeSyncApp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.ayushkumar.localSync.presentation.StringManager
import javax.inject.Inject

@HiltAndroidApp
class LocaleSync: Application() {
    @Inject
    lateinit var bsStringManager: StringManager

    override fun onCreate() {
        super.onCreate()

        bsStringManager.initialize("https://www.jsonkeeper.com/b/GTACT")
        bsStringManager.setLanguage("en")

    }

}