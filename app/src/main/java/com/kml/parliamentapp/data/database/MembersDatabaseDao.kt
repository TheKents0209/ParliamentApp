package com.kml.parliamentapp.data.database

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Needed Querys for searching MembersDatabase
* */

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kml.parliamentapp.data.model.ParliamentMember
import com.kml.parliamentapp.data.model.Party

@Dao
interface MemberDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ParliamentMember>)

    @Update
    suspend fun update(member: ParliamentMember)

    @Query("SELECT * FROM parliament_members_table WHERE hetekaId = :hetekaId")
    fun getMemberById(hetekaId: Int): LiveData<ParliamentMember>

    @Query("SELECT * FROM parliament_members_table ORDER BY RANDOM() LIMIT 1")
    fun getRandomMember(): LiveData<ParliamentMember>

    @Query("SELECT * FROM parliament_members_table")
    fun getMembers(): LiveData<List<ParliamentMember>>

    @Query("SELECT * FROM parliament_members_table WHERE party = :party ORDER BY full_name ASC")
    fun getMembersByParty(party: String): LiveData<List<ParliamentMember>>

    @Query("SELECT party FROM parliament_members_table  GROUP BY party ORDER BY party ASC")
    fun getParties(): LiveData<List<Party>>
}