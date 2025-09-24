package io.github.ayushkumar.localSync.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.ayushkumar.localSync.data.remote.LocaleSyncAPI
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocaleSyncModule {
    @Provides
    @Singleton
    fun providesLocalSyncAPI(retrofit: Retrofit): LocaleSyncAPI{
        return retrofit.create(LocaleSyncAPI::class.java)
    }



}