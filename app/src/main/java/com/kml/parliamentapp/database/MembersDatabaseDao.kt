package com.kml.parliamentapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kml.parliamentapp.models.ParliamentMember

@Dao
interface MembersDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: ParliamentMember)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ParliamentMember>)

    @Update
    suspend fun update(member: ParliamentMember)

    @Query("DELETE FROM parliament_members_table")
    fun clear()

    @Query("SELECT * FROM parliament_members_table WHERE hetekaId = :hetekaId")
    suspend fun getMemberById(hetekaId: Int): ParliamentMember?

    @Query("SELECT * FROM parliament_members_table LIMIT 1")
    suspend fun getRandomMember(): ParliamentMember?

    @Query("SELECT * FROM parliament_members_table")
    fun getAllMembers(): LiveData<List<ParliamentMember>>
}