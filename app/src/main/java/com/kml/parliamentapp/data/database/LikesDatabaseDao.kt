package com.kml.parliamentapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kml.parliamentapp.data.model.Likes
@Dao
interface LikesDatabaseDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(members: Likes)

    @Query("UPDATE likes_table SET likes = likes + 1 WHERE hetekaId = :id")
    suspend fun likeMember(id: Int)

    @Query("UPDATE likes_table SET likes = likes - 1 WHERE hetekaId = :id")
    suspend fun dislikeMember(id: Int)

    @Query("SELECT * FROM likes_table WHERE hetekaId = :id")
    fun getLikes(id: Int) : LiveData<Likes>
}