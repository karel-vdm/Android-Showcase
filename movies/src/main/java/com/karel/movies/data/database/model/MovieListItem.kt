package com.karel.movies.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_list_items")
data class MovieListItem(
    @PrimaryKey(autoGenerate = true)
    val uId: Int? = null,
    val id: String = String(),
    val title: String = String(),
    val poster: String = String(),
    val type: String = String(),
    val year: String = String()
)