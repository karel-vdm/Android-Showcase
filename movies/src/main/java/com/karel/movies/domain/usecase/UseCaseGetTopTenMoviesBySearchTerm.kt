package com.karel.movies.domain.usecase

import com.karel.movies.data.api.model.MovieSearchDto
import com.karel.movies.data.repository.MovieRepository
import com.karel.movies.domain.model.MovieListEntity
import com.karel.movies.domain.model.transformer.TransformerMovieListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UseCaseGetTopTenMoviesBySearchTerm(
    private val repository: MovieRepository
) {
    fun getTopTenMoviesBySearchTerm(searchTerm: String): Flow<MovieListEntity> {
        return repository.getMovies(searchTerm, 1).map { response ->
            val filteredResults = response.copy(
                Search = response.Search
                    ?.sortedWith(compareByDescending<MovieSearchDto> { it.imdbID }
                        .thenByDescending { it.Title })
                    ?.take(10)
                    ?: emptyList()
            )
            TransformerMovieListEntity.transform(filteredResults)
        }
    }
}