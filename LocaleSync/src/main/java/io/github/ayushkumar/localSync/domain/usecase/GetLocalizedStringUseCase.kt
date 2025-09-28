package io.github.ayushkumar.localSync.domain.usecase

import io.github.ayushkumar.localSync.data.repository.LocaleSyncRepository
import javax.inject.Inject

class GetLocalizedStringUseCase @Inject constructor(
    private val repository: LocaleSyncRepository
) {
    operator fun invoke(language: String, key: String, args: List<Any>): String {
        val rawString = repository.getString(language, key)
        return try {
            String.format(rawString, *args.toTypedArray())
        } catch (e: Exception) {
            rawString
        }
    }
}
