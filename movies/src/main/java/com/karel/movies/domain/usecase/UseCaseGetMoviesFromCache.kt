package com.karel.movies.domain.usecase

import com.karel.movies.data.repository.MovieRepository
import com.karel.movies.domain.model.MovieListItemEntity
import com.karel.movies.domain.model.transformer.TransformerMovieListItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UseCaseGetMoviesFromCache(
    private val repository: MovieRepository
) {
    fun getMovieListItems(): Flow<List<MovieListItemEntity>>? =
        repository.getMoviesFromCache()?.map { results ->
            results.map {
                TransformerMovieListItemEntity.transform(it)
            }
        }
}