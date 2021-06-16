package com.yhogie.made.moviecatalogue.home

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import com.yhogie.made.core.domain.usecase.MovieUseCase
import com.yhogie.made.core.data.source.remote.response.Resource
import com.yhogie.made.core.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val _movie = MutableLiveData<Resource<List<Movie>>>()
    val movie: LiveData<Resource<List<Movie>>> = _movie

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            movieUseCase.getPopularMovie().collect { result ->
                _movie.value = result
            }
        }
    }

}


