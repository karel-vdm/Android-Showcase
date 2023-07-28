package com.karel.movies.domain.model.transformer

import com.karel.movies.domain.model.MovieListEntity
import com.karel.movies.data.api.model.GetMoviesResponseDto
import com.karel.movies.data.database.model.MovieListState

object TransformerMovieListEntity {

    fun transform(dto: GetMoviesResponseDto) = MovieListEntity(
        isSuccess = dto.Response?.toBoolean() ?: false,
        pagingInfo = TransformerPagingInfoEntity.transform(dto),
        movies = dto.Search?.map {
            TransformerMovieListItemEntity.transform(it)
        } ?: emptyList(),
        error = dto.Error ?: "",
    )

    fun transform(entity: MovieListEntity) = MovieListState(
        page = entity.pagingInfo.page,
        pageSize = entity.pagingInfo.pageSize,
        totalResults = entity.pagingInfo.totalResults,
        totalResultsLoaded = entity.pagingInfo.totalResultsLoaded,
        currentPosition = entity.pagingInfo.currentPosition,
        searchTerm = entity.searchTerm
    )

    fun transform(model: MovieListState) = MovieListEntity(
        searchTerm = model.searchTerm,
        pagingInfo = TransformerPagingInfoEntity.transform(model)
    )
}