package com.karel.movies.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karel.movies.domain.usecase.UseCaseGetFavoriteMovies
import com.karel.movies.domain.usecase.UseCaseGetMovies
import com.karel.movies.domain.usecase.UseCaseGetTopTenMoviesBySearchTerm

class SearchViewModelFactory(
    private val useCaseGetMovies: UseCaseGetMovies
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            UseCaseGetMovies::class.java
        ).newInstance(
            useCaseGetMovies
        )
    }

}