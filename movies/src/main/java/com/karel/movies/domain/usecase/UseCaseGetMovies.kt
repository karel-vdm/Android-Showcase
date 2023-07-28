package com.karel.movies.domain.usecase

import com.karel.movies.data.repository.MovieRepository
import com.karel.movies.domain.model.MovieListEntity
import com.karel.movies.domain.model.transformer.TransformerMovieListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UseCaseGetMovies(
    private val repository: MovieRepository
) {
    fun getMoviesBySearchTerm(searchTerm: String, page: Int): Flow<MovieListEntity> =
        repository.getMovies(searchTerm, page).map { response ->
            TransformerMovieListEntity.transform(response)
        }
}