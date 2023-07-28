package com.karel.movies.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieRatingDto(

    @SerializedName("Source")
    val Source: String? = null,

    @SerializedName("Value")
    val Value: String? = null
)