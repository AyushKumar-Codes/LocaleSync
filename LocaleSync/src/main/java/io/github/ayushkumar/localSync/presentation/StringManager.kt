package io.github.ayushkumar.localSync.presentation

import io.github.ayushkumar.localSync.data.repository.LocaleSyncRepository
import io.github.ayushkumar.localSync.domain.usecase.GetLocalizedStringUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringManager @Inject constructor(
    private val useCase: GetLocalizedStringUseCase,
    private val repository: LocaleSyncRepository
) {
    private var selectedLanguage: String = "en"
    private var localizationUrl: String? = null

    fun setLanguage(language: String) {
        selectedLanguage = language
    }

    fun getString(key: String, vararg args: Any): String {
        return useCase(selectedLanguage, key, args.toList())
    }

    /**
     * Initialize once at app startup
     */
    fun initialize(fullUrl: String) {
        localizationUrl = fullUrl
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchAndCacheLocalization(fullUrl)
        }
    }

    /**
     * Update localization strings from the previously initialized URL
     * Optional callback for success/failure
     */
    fun updateLocalization(onComplete: ((success: Boolean) -> Unit)? = null) {
        val url = localizationUrl
        if (url == null) {
            onComplete?.let { it(false) } // URL not initialized
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.fetchAndCacheLocalization(url)
                onComplete?.let { it(true) }
            } catch (e: Exception) {
                e.printStackTrace()
                onComplete?.let { it(false) }
            }
        }
    }
}
