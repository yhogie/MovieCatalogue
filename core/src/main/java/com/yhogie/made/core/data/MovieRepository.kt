package com.yhogie.made.core.data

import com.yhogie.made.core.data.source.local.LocalDataSource
import com.yhogie.made.core.data.source.remote.RemoteDataSource
import com.yhogie.made.core.data.source.remote.api.ApiResponse
import com.yhogie.made.core.data.source.remote.response.MovieResponse
import com.yhogie.made.core.domain.model.Movie
import com.yhogie.made.core.domain.repository.IMovieRepository
import com.yhogie.made.core.utils.AppExecutors
import com.yhogie.made.core.utils.DataMapper
import com.yhogie.made.core.data.source.remote.response.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getPopularMovie(): Flow<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie()
                    .map { DataMapper.mapMovieEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            public override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<Movie>> {
        return object :
            NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieDetail(movieId)
                    .map {
                        DataMapper.mapMovieEntityToDomain(it) }
            }


            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieDetail(movieId)

            override suspend fun saveCallResult(data: MovieResponse) {
                val movie = DataMapper.mapMovieResponseToEntity(data)
                localDataSource.updateMovie(movie)
            }

            override fun shouldFetch(data: Movie?): Boolean {
                TODO("Not yet implemented")
            }

        }.asFlow()
    }



    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun setFavouriteMovie(movie: Movie, isFavourite: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, isFavourite)}
    }
}