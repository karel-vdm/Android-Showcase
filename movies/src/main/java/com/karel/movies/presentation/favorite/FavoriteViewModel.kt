package com.karel.movies.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karel.movies.domain.model.MovieListEntity
import com.karel.movies.domain.usecase.UseCaseGetFavoriteMovies
import com.karel.movies.domain.usecase.UseCaseGetTopTenMoviesBySearchTerm
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val useCaseGetFavoriteMovies: UseCaseGetFavoriteMovies
) : ViewModel() {

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var _favoriteMovies = MutableLiveData<MovieListEntity>()
    val favoriteMovies: LiveData<MovieListEntity> get() = _favoriteMovies

    fun onViewCreated() {
        viewModelScope.launch {

            useCaseGetFavoriteMovies.getFavoriteMovies()
                .onStart {
                    onLoading()
                }
                .catch {
                    onError(it.message ?: "Error getting movies please try again")
                }
                .collect { result ->
                    if (result.isSuccess) {
                        onCompleted(_favoriteMovies, result)
                    } else {
                        onError(result.error)
                    }
                }
        }
    }

    private fun onCompleted(liveData: MutableLiveData<MovieListEntity>, result: MovieListEntity) {
        _loading.postValue(false)
        liveData.postValue(result)
    }

    private fun onError(error: String) {
    }

    private fun onLoading() {
        _loading.postValue(true)
    }

}