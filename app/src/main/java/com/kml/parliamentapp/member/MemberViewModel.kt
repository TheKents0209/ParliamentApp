package com.kml.parliamentapp.member

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kml.parliamentapp.database.ParliamentMember
import com.kml.parliamentapp.database.members

class MemberViewModel : ViewModel() {

    var randomMember = members.randomMember()

    private val _likes = MutableLiveData<Int>()
    val likes: LiveData<Int>
        get() = _likes

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
        _likes.value = 0
        _firstName.value = randomMember.firstname
        _lastName.value = randomMember.lastname
        _party.value = randomMember.party
        Log.i("MemberViewModel", "MemberViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("MemberViewModel", "MemberViewModel destroyed!")
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
}