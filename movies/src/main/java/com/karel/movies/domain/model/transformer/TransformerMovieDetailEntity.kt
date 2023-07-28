package com.karel.movies.domain.model.transformer

import com.karel.movies.domain.model.MovieDetailEntity
import com.karel.movies.data.api.model.GetMovieDetailResponseDto

object TransformerMovieDetailEntity {

    fun transform(dto: GetMovieDetailResponseDto) = MovieDetailEntity(
        id = dto.imdbID ?: String(),
        title = dto.Title ?: String(),
        poster = dto.Poster ?: String(),
        type = dto.Type ?: String(),
        year = dto.Year ?: String(),
        ageRestriction = dto.Rated ?: String(),
        runtime = dto.Runtime ?: String(),
        rating = dto.imdbRating ?: String(),
        plot = dto.Plot ?: String()
    )
}