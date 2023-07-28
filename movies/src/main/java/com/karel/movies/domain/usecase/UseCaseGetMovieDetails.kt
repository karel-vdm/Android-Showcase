package com.karel.movies.domain.usecase

import com.karel.movies.data.repository.MovieRepository
import com.karel.movies.domain.model.MovieDetailEntity
import com.karel.movies.domain.model.transformer.TransformerMovieDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UseCaseGetMovieDetails(
    private val repository: MovieRepository
) {
    fun getMovieById(id: String): Flow<MovieDetailEntity> =
        repository.getMovieById(id).map { response ->
            TransformerMovieDetailEntity.transform(response)
        }
}