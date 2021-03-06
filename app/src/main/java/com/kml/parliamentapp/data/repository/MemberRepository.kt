package com.kml.parliamentapp.data.repository

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Repository that is linked to MembersDatabaseDao and includes methods for database search
* */

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kml.parliamentapp.data.database.MemberDatabaseDao
import com.kml.parliamentapp.data.model.ParliamentMember
import com.kml.parliamentapp.data.api.ParliamentApi
import com.kml.parliamentapp.data.api.ParliamentApiStatus
import com.kml.parliamentapp.data.model.Party
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemberRepository(private val databaseDao: MemberDatabaseDao) {

    private val _status = MutableLiveData<ParliamentApiStatus>()
    val status: LiveData<ParliamentApiStatus>
        get() = _status


    init {
        _status.postValue(ParliamentApiStatus.LOADING)
    }

    //Gets List of ParliamentMember objects and inserts them to database
    suspend fun refreshParliamentMembers() {
        withContext(Dispatchers.IO) {
            val members = ParliamentApi.members.getMembers()
            _status.postValue(ParliamentApiStatus.DONE)
            databaseDao.insertAll(members)
        }
    }

    fun getParliamentMemberWithId(id : Int) : LiveData<ParliamentMember> {
        return databaseDao.getMemberById(id)
    }

    fun getParliamentMembersByParty(party: String) : LiveData<List<ParliamentMember>> {
        return databaseDao.getMembersByParty(party)
    }

    fun getParties() : LiveData<List<Party>> {
        return databaseDao.getParties()
    }
}
