package com.karel.movies.domain.model.transformer

import com.karel.movies.domain.model.PagingInfoEntity
import com.karel.movies.data.api.model.GetMoviesResponseDto
import com.karel.movies.data.database.model.MovieListState

object TransformerPagingInfoEntity {

    fun transform(dto: GetMoviesResponseDto) = PagingInfoEntity(
        pageSize = dto.Search?.size ?: 0,
        totalResults = dto.totalResults?.toIntOrNull() ?: 0
    )

    fun transform(model: MovieListState) = PagingInfoEntity(
        page = model.page,
        pageSize = model.pageSize,
        currentPosition = model.currentPosition,
        totalResults = model.totalResults,
        totalResultsLoaded = model.totalResultsLoaded
    )
}