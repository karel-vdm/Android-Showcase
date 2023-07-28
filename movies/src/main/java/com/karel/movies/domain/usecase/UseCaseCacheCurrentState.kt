package com.karel.movies.domain.usecase

import com.karel.movies.data.repository.MovieRepository
import com.karel.movies.domain.model.MovieListEntity
import com.karel.movies.domain.model.transformer.TransformerMovieListEntity

class UseCaseCacheCurrentState(
    private val repository: MovieRepository
) {
    suspend fun cacheMovies(movie: MovieListEntity) {
        repository.saveCurrentState(TransformerMovieListEntity.transform(movie))
    }
}