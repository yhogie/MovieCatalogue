package com.yhogie.made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val overview: String,
    val score: Double,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String,
    val popularity: Double,
    val voteCount: Int,
    val isFavorite: Boolean
) : Parcelable
