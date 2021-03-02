package com.kml.parliamentapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likes_table")
data class Likes(
    @PrimaryKey
    val hetekaId: Int,
    var likes: Int = 0
)
