package com.karel.movies.domain.usecase

import com.karel.movies.data.repository.MovieRepository

class UseCaseClearSavedState(
    private val repository: MovieRepository
) {
    suspend fun clearSavedState() {
        repository.clearSavedState()
        repository.deleteMoviesFromCache()
    }
}