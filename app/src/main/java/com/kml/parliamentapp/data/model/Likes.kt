package com.kml.parliamentapp.data.model

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Model for Likes database
* */

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likes_table")
data class Likes(
    @PrimaryKey
    val hetekaId: Int,
    var likes: Int = 0
)
