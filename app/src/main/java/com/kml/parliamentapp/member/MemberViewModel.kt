package com.kml.parliamentapp.member

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.*
import com.kml.parliamentapp.database.*
import com.kml.parliamentapp.formatMembers
import com.kml.parliamentapp.network.ParliamentApi
import com.squareup.moshi.Json
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MemberViewModel(
    val database: MembersDatabaseDao,
    application: Application,
    hetekaId: Int
) : AndroidViewModel(application) {

    var randomMember = members.randomMember()

    private var parliamentMember = MutableLiveData<ParliamentMember?>()

    private val allMembers = database.getAllMembers()
    val allMemberString = Transformations.map(allMembers) { allMembers ->
        formatMembers(allMembers, application.resources)
    }

//    private val _hetekaId = MutableLiveData<Int>()
//    val hetekaId: LiveData<Int>
//        get() = _hetekaId

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _likes = MutableLiveData<Int>()
    val likes: LiveData<Int>
        get() = _likes

    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String>
        get() = _fullName

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String>
        get() = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String>
        get() = _lastName

    private val _party = MutableLiveData<String>()
    val party: LiveData<String>
        get() = _party

    init {
        initializeParliamentMember(hetekaId)
        _likes.value = 0
//        _firstName.value = parliamentMember.value!!.firstname
        _lastName.value = randomMember.lastname
        _party.value = randomMember.party
        Log.i("MemberViewModel", "MemberViewModel created!")
        Log.i("MemberViewModel", "Clicked members id is $hetekaId")
    }

    private fun initializeParliamentMember(hetekaId: Int) {
        viewModelScope.launch {
            parliamentMember.value = getMemberById(hetekaId)
            Log.i("MemberViewModel", parliamentMember.value!!.fullname)
            _fullName.value = parliamentMember.value?.fullname
            _party.value = parliamentMember.value?.party
        }
    }

    private suspend fun getMemberById(hetekaId: Int): ParliamentMember? {
        var clickedMember = database.getMemberById(hetekaId)
        if (clickedMember?.firstname.isNullOrEmpty()) {
            clickedMember = null
        }
        return clickedMember
    }

    private suspend fun getRandomMemberFromDatabase(): ParliamentMember? {
        var randomMember = database.getRandomMember()
        if (randomMember?.firstname.isNullOrEmpty()) {
            randomMember = null
        }
        return randomMember
    }

    fun dislike() {
        _likes.value = (_likes.value)?.minus(1)
    }

    fun like() {
        _likes.value = (_likes.value)?.plus(1)
    }

    fun randomMember() {
        randomMember = members.randomMember()
        _likes.value = 0
        _firstName.value = randomMember.firstname
        _lastName.value = randomMember.lastname
        _party.value = randomMember.party
    }

//    private fun getEduskuntaMembers() {
//        viewModelScope.launch {
//            try {
//                val listResult = ParliamentApi.retrofitService.getMembers()
//
//                _response.value =
//                    "Success: ${listResult.size} Eduskunta members retrieved"
//                database.insertAll(listResult)
//            } catch (e: Exception) {
//                _response.value = "Failure: ${e.message}"
//            }
//        }
//    }
}