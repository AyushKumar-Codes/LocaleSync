package io.github.ayushkumar.localSync.data.repository

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.ayushkumar.localSync.data.remote.LocaleSyncRemoteDataSource
import io.github.ayushkumar.localSync.utils.LocalizationStorage
import javax.inject.Inject

class LocaleSyncRepository @Inject constructor(
    private val remote: LocaleSyncRemoteDataSource,
    private val storage: LocalizationStorage,
    @ApplicationContext val context: Context
) {

    suspend fun fetchAndCacheLocalization(url: String) {
        try {
            val response = remote.downloadLocalisation(url)
            storage.saveLocalization(response)
        } catch (e: Exception) {
            Log.e("LocalizationRepo", "Failed to fetch localization: ${e.message}")
        }
    }

    /**
     * Get localized string from cache or fallback to resources.
     */
    fun getString(language: String, key: String, fallback: String? = null): String {
        val translations = storage.getLocalization()
        // 1️⃣ Try cached backend string
        translations[language]?.get(key)?.let { return it }

        // 2️⃣ Try app resources
        val resId = context.resources.getIdentifier(key, "string", context.packageName)
        if (resId != 0) {
            return context.getString(resId)
        }

        // 3️⃣ Fallback error
        return fallback ?: "!!missing!!"
    }

}