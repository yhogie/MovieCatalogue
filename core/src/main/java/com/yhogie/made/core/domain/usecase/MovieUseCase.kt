package com.yhogie.made.core.domain.usecase

import com.yhogie.made.core.data.source.remote.response.Resource
import com.yhogie.made.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovie() : Flow<Resource<List<Movie>>>

    fun getMovieDetail(movieId: Int): Flow<Resource<Movie>>

    fun getFavoriteMovie() : Flow<List<Movie>>

    fun setFavouriteMovie(movie: Movie, isFavourite: Boolean)
}