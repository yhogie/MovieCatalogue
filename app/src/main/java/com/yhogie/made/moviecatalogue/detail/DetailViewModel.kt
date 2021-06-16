package com.yhogie.made.moviecatalogue.detail


import androidx.lifecycle.ViewModel
import com.yhogie.made.core.domain.model.Movie
import com.yhogie.made.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {


    fun setFavoriteMovie(movie: Movie, status: Boolean) = movieUseCase.setFavouriteMovie(movie, status)
}