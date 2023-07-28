package com.karel.movies.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karel.movies.domain.usecase.UseCaseGetFavoriteMovies
import com.karel.movies.domain.usecase.UseCaseGetTopTenMoviesBySearchTerm

class FavoriteViewModelFactory(
    private val useCaseGetFavoriteMovies: UseCaseGetFavoriteMovies
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            UseCaseGetFavoriteMovies::class.java
        ).newInstance(
            useCaseGetFavoriteMovies
        )
    }

}