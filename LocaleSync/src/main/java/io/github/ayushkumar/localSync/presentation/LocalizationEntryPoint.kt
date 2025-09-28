package io.github.ayushkumar.localSync.presentation

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LocalizationEntryPoint {
    fun bsStringManager(): StringManager
}