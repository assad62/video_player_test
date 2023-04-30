package com.silverorange.videoplayer.di

import com.silverorange.videoplayer.common.Constants
import com.silverorange.videoplayer.data.remote.VideosApi
import com.silverorange.videoplayer.data.repository.VideosRepositoryImpl
import com.silverorange.videoplayer.domain.repository.VideosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVideosApi(): VideosApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VideosApi::class.java)
    }

    @Provides
    @Singleton
    fun provideVideosRepository(api: VideosApi): VideosRepository {
        return VideosRepositoryImpl(api)
    }
}