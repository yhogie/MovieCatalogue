package com.yhogie.made.core.di

import com.yhogie.made.core.data.MovieRepository
import com.yhogie.made.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository) : IMovieRepository

}