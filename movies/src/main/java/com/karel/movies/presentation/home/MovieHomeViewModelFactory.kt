package com.karel.movies.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karel.movies.domain.usecase.UseCaseGetFavoriteMovies
import com.karel.movies.domain.usecase.UseCaseGetTopTenMoviesBySearchTerm

class MovieHomeViewModelFactory(
    private val useCaseGetTopTenMoviesBySearchTerm: UseCaseGetTopTenMoviesBySearchTerm,
    private val useCaseGetFavoriteMovies: UseCaseGetFavoriteMovies
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            UseCaseGetTopTenMoviesBySearchTerm::class.java,
            UseCaseGetFavoriteMovies::class.java
        ).newInstance(
            useCaseGetTopTenMoviesBySearchTerm,
            useCaseGetFavoriteMovies
        )
    }

}