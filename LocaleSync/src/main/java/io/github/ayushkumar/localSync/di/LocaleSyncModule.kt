package io.github.ayushkumar.localSync.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import io.github.ayushkumar.localSync.data.remote.LocaleSyncAPI
import io.github.ayushkumar.localSync.data.remote.LocaleSyncRemoteDataSource
import io.github.ayushkumar.localSync.data.repository.LocaleSyncRepository
import io.github.ayushkumar.localSync.utils.LocalizationStorage
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

    @Provides
    @Singleton
    fun providesLocalSyncRemoteDataSource(api : LocaleSyncAPI) : LocaleSyncRemoteDataSource{
        return LocaleSyncRemoteDataSource(api)
    }
    @Provides
    @Singleton
    fun providesLocalSyncRepository(remote: LocaleSyncRemoteDataSource , storage: LocalizationStorage , @ApplicationContext context: Context) : LocaleSyncRepository{
        return LocaleSyncRepository(remote,storage , context)
    }



}