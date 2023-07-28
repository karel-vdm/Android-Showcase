package com.karel.movies.domain.model

import android.net.Uri
import androidx.core.net.toUri

data class MovieListEntity(
    val isSuccess: Boolean = false,
    val searchTerm: String = String(),
    val pagingInfo: PagingInfoEntity = PagingInfoEntity(),
    val movies: List<MovieListItemEntity> = emptyList(),
    val error: String = String()
) {
    val hasNoResults: Boolean
        get() {
            return movies.isEmpty()
        }

    val transformMoviesToUriList: List<Uri>
        get() {
            return movies.map { it.poster.toUri() }
        }
}

