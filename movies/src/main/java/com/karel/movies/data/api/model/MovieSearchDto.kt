package com.karel.movies.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieSearchDto(

    @SerializedName("Poster")
    val Poster: String? = null,

    @SerializedName("Title")
    val Title: String? = null,

    @SerializedName("Type")
    val Type: String? = null,

    @SerializedName("Year")
    val Year: String? = null,

    @SerializedName("imdbID")
    val imdbID: String? = null
)