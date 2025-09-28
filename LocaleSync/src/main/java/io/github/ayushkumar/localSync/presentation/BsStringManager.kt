package io.github.ayushkumar.localSync.presentation

import io.github.ayushkumar.localSync.data.repository.LocaleSyncRepository
import io.github.ayushkumar.localSync.domain.usecase.GetLocalizedStringUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BsStringManager @Inject constructor(
    private val useCase: GetLocalizedStringUseCase,
    private val repository: LocaleSyncRepository
) {
    private var selectedLanguage: String = "en"

    fun setLanguage(language: String) {
        selectedLanguage = language
    }

    fun getString(key: String, vararg args: Any): String {
        return useCase(selectedLanguage, key, args.toList())
    }

    fun initialize(fullUrl: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchAndCacheLocalization(fullUrl)
        }
    }
}