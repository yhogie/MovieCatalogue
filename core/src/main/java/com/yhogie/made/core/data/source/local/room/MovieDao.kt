package com.yhogie.made.core.data.source.local.room

import androidx.room.*
import com.yhogie.made.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie WHERE movieId= :movieId")
    fun getMovieDetail(movieId: Int): Flow<MovieEntity>

    @Update
    suspend fun updateMovie(movie: MovieEntity)

}