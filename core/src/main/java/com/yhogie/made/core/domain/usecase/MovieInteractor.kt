package com.yhogie.made.core.domain.usecase

import com.yhogie.made.core.data.source.remote.response.Resource
import com.yhogie.made.core.domain.model.Movie
import com.yhogie.made.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getPopularMovie(): Flow<Resource<List<Movie>>> = movieRepository.getPopularMovie()

    override fun getMovieDetail(movieId: Int) = movieRepository.getMovieDetail(movieId)

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavouriteMovie(movie: Movie, isFavourite: Boolean) = movieRepository.setFavouriteMovie(movie, isFavourite)

}