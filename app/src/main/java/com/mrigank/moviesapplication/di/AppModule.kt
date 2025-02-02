package com.mrigank.moviesapplication.di

import com.mrigank.moviesapplication.Constants.BASE_URL
import com.mrigank.moviesapplication.networking.MoviesService
import com.mrigank.moviesapplication.repository.Repository
import com.mrigank.moviesapplication.repository.RepositoryImpl
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
    fun provideMoviesService() : MoviesService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesService: MoviesService) : Repository {
        return RepositoryImpl(
            moviesService = moviesService
        )
    }
}