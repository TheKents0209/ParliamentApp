package com.kml.parliamentapp.data.repository

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Repository that is linked to LikesDatabaseDao and includes methods for database search
* */

import android.util.Log
import androidx.lifecycle.LiveData
import com.kml.parliamentapp.data.database.MemberDatabaseDao
import com.kml.parliamentapp.data.model.ParliamentMember
import com.kml.parliamentapp.data.api.ParliamentApi
import com.kml.parliamentapp.data.model.Party
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemberRepository(private val databaseDao: MemberDatabaseDao) {


    suspend fun refreshParliamentMembers() {
        withContext(Dispatchers.IO) {
            val members = ParliamentApi.members.getMembers()
            Log.i("MemberRepository", "Refreshed database with ${members.size} members")
            databaseDao.insertAll(members)
        }
    }
    suspend fun updateParliamentMember(member: ParliamentMember) {
        databaseDao.update(member)
    }
    fun getParliamentMembers() : LiveData<List<ParliamentMember>> {
        return databaseDao.getMembers()
    }

    fun getParliamentMemberWithId(id : Int) : LiveData<ParliamentMember> {
        return databaseDao.getMemberById(id)
    }

    fun getRandomMember(): LiveData<ParliamentMember> {
        return databaseDao.getRandomMember()
    }

    fun getParliamentMembersByParty(party: String) : LiveData<List<ParliamentMember>> {
        return databaseDao.getMembersByParty(party)
    }

    fun getParties() : LiveData<List<Party>> {
        return databaseDao.getParties()
    }

//    fun correctFullPartyNames(list: List<ParliamentMember>) : List<ParliamentMember> {
//        list.forEach{
//            if(it.party == "ps") {
//                it.party = "Perussuomalaiset"
//            }
//            if(it.party == "sd") {
//                it.party = "Suomen Sosialidemokraattinen Puolue"
//            }
//            if(it.party == "kok") {
//                it.party = "Kansallinen Kokoomus"
//            }
//            if(it.party == "vihr") {
//                it.party = "Vihreä liitto"
//            }
//            if(it.party == "vas") {
//                it.party = "Vasemmistoliitto"
//            }
//            if(it.party == "r") {
//                it.party = "Suomen ruotsalainen kansanpuolue"
//            }
//            if(it.party == "kesk") {
//                it.party = "Suomen Keskusta"
//            }
//            if(it.party == "kd") {
//                it.party = "Suomen Kristillisdemokraatit"
//            }
//            if(it.party == "liik") {
//                it.party = "Liike Nyt"
//            }
//        }
//        return list
//    }


//    suspend fun updateParliamentMember(member: ParliamentMember) {
//        databaseDao.update(member)
//    }
//
//    suspend fun refreshParliamentMembers() {
//        withContext(Dispatchers.IO) {
//            Log.d("MemberRepository", "refresh called")
//            val listResult = ParliamentApi.retrofitService.getMembers()
//            databaseDao.insertAll(listResult)
//        }
//    }
}
