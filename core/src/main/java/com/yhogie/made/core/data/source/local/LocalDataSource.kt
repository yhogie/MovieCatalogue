package com.yhogie.made.core.data.source.local

import com.yhogie.made.core.data.source.local.entity.MovieEntity
import com.yhogie.made.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val mmovieDao: MovieDao) {

    fun getAllMovie() : Flow<List<MovieEntity>> = mmovieDao.getMovie()

    fun getMovieDetail(movieId: Int): Flow<MovieEntity> = mmovieDao.getMovieDetail(movieId)

    fun getFavoriteMovie() : Flow<List<MovieEntity>> = mmovieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = mmovieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mmovieDao.updateFavoriteMovie(movie)
    }

    suspend fun updateMovie(movie: MovieEntity) = mmovieDao.updateMovie(movie)

}