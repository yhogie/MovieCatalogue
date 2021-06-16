package com.yhogie.made.core.data.source.remote.api

import com.yhogie.made.core.BuildConfig
import com.yhogie.made.core.data.source.remote.response.MovieResponse
import com.yhogie.made.core.data.source.remote.response.Response
import com.yhogie.made.core.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("discover/movie")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey : String = BuildConfig.API_KEY,
        @Query("language") language: String = Constants.language
    ): Response

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey : String = BuildConfig.API_KEY,
        @Query("language") language: String = Constants.language
    ): MovieResponse


}