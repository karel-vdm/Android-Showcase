package com.karel.movies.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_detail")
data class MovieDetail(

    @PrimaryKey
    val id: String = String()

)