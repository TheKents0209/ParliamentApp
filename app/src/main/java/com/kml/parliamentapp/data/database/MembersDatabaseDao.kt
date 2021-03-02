package com.kml.parliamentapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kml.parliamentapp.data.model.ParliamentMember
import com.kml.parliamentapp.data.model.Party
import kotlinx.android.synthetic.main.fragment_member.view.*

@Dao
interface MemberDatabaseDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(member: ParliamentMember)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ParliamentMember>)

    @Update
    suspend fun update(member: ParliamentMember)
//
//    @Query("DELETE FROM parliament_members_table")
//    fun clear()
//
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