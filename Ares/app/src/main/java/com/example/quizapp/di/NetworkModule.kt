package com.example.quizapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import football.seriaa.data.remote.RetrofitClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient() = RetrofitClient()

    @Singleton
    @Provides
    fun provideCategoriesApiService(retrofitClient: RetrofitClient) =
        retrofitClient.provideCategoriesApiService()
}