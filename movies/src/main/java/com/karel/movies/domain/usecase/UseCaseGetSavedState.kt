package com.karel.movies.domain.usecase

import com.karel.movies.data.repository.MovieRepository
import com.karel.movies.domain.model.MovieListEntity
import com.karel.movies.domain.model.transformer.TransformerMovieListEntity

class UseCaseGetSavedState(
    private val repository: MovieRepository
) {

    suspend fun getSavedState(): MovieListEntity {
        return TransformerMovieListEntity.transform(repository.getSavedState())
    }

}