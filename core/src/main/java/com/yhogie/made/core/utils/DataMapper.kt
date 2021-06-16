package com.yhogie.made.core.utils

import com.yhogie.made.core.data.source.local.entity.MovieEntity
import com.yhogie.made.core.data.source.remote.response.MovieResponse
import com.yhogie.made.core.domain.model.Movie

object DataMapper {
    fun mapResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                score = it.score,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                voteCount = it.voteCount,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId= it.movieId,
                title = it.title,
                overview = it.overview,
                score = it.score,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
        movieId  = input.movieId,
        title = input.title,
        overview = input.overview,
        score = input.score,
        releaseDate = input.releaseDate,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        popularity = input.popularity,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )

    fun mapMovieResponseToEntity(input: MovieResponse) = MovieEntity(
        movieId  = input.movieId,
        title = input.title,
        overview = input.overview,
        score = input.score,
        releaseDate = input.releaseDate,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        popularity = input.popularity,
        voteCount = input.voteCount,
        isFavorite = input.isFavorited
    )


    fun mapMovieEntityToDomain(input: MovieEntity) = Movie(
        movieId  = input.movieId,
        title = input.title,
        overview = input.overview,
        score = input.score,
        releaseDate = input.releaseDate,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        popularity = input.popularity,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )


}