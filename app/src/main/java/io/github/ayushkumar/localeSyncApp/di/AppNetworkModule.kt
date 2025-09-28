package io.github.ayushkumar.localeSyncApp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.ayushkumar.localSync.data.repository.LocaleSyncRepository
import io.github.ayushkumar.localSync.domain.usecase.GetLocalizedStringUseCase
import io.github.ayushkumar.localSync.presentation.StringManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppNetworkModule {

    private const val BASE_URL = "https://your-api-url.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // attach OkHttp client
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


//    private const val DEFAULT_URL = "https://www.jsonkeeper.com/b/GTACT"
//
//    @Provides
//    @Singleton
//    fun provideStringManager(
//        useCase: GetLocalizedStringUseCase,
//        repository: LocaleSyncRepository
//    ): StringManager {
//        return StringManager(useCase, repository).apply {
//            initialize(DEFAULT_URL)
//        }
//    }
}
