package com.kml.parliamentapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MembersDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: ParliamentMember)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ParliamentMember>)

    @Update
    suspend fun update(member: ParliamentMember)

    @Query("SELECT * FROM parliament_members_table WHERE hetekaId = :hetekaId")
    suspend fun getById(hetekaId: Int): ParliamentMember?

    @Query("SELECT * FROM parliament_members_table LIMIT 1")
    suspend fun getRandomMember(): ParliamentMember?

    @Query("SELECT * FROM parliament_members_table")
    fun getAllMembers(): LiveData<List<ParliamentMember>>
}