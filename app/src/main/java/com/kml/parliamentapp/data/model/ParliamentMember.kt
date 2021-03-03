package com.kml.parliamentapp.data.model

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Model for Likes database, API
* */

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parliament_members_table")
data class ParliamentMember(
    @PrimaryKey(autoGenerate = false)
    val hetekaId: Int,

    @ColumnInfo(name = "seat_number")
    val seatNumber: Int,

    @ColumnInfo(name = "last_name")
    var lastname: String,

    @ColumnInfo(name = "first_name")
    var firstname: String,

    @ColumnInfo(name = "full_name")
    var fullname: String = "$firstname $lastname",

    @ColumnInfo(name = "party")
    var party: String,

    @ColumnInfo(name = "minister")
    val minister: Boolean,

    @ColumnInfo(name = "picture_url")
    val pictureUrl: String = ""
)