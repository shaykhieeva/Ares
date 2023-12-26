package com.example.quizapp.di

import football.seriaa.domain.repositories.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import football.seriaa.data.repositories.CategoriesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindCategoryRepositories(repositoryImpl: CategoriesRepositoryImpl): CategoriesRepository
}