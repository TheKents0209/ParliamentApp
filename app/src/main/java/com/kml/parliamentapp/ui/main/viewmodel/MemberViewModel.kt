package com.kml.parliamentapp.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.kml.parliamentapp.data.model.ParliamentMember
import com.kml.parliamentapp.data.repository.MemberRepository
import kotlinx.coroutines.launch

class MemberViewModel(
    private val memberRepository: MemberRepository,
    hetekaIdKey: Int
) : ViewModel() {

    // = memberRepository.getParliamentMemberWithId(hetekaIdKey)
    private var _member = MutableLiveData<LiveData<ParliamentMember>>()
    val member: LiveData<LiveData<ParliamentMember>>
    get() = _member

    init {
        _member.value = memberRepository.getParliamentMemberWithId(hetekaIdKey)
    }


    //TODO: Partial update implementation
    fun likeParliamentMember() {
        viewModelScope.launch {
            val memberLikeAmount = member.value?.value?.likes
            val memberNewLikeAmount = memberLikeAmount?.plus(1) ?: 0

            if(member.value != null) {
                val mParliamentMember = member.value?.value?.let {
                    ParliamentMember(
                        it.hetekaId,
                        it.seatNumber,
                        it.lastname,
                        it.firstname,
                        it.fullname,
                        it.party,
                        it.minister,
                        it.pictureUrl,
                        memberNewLikeAmount
                    )
                }
                if (mParliamentMember != null) {
                    memberRepository.updateParliamentMember(mParliamentMember)
                    Log.i("MemberViewModel", "member liked")
                } else {
                    Log.i("MemberViewModel", "member was null")
                }
            }
        }
    }
    //TODO:Dislike member

    fun randomMember() {
        viewModelScope.launch {
            Log.i("MemberViewModel", "randomMember method launced")
            Log.i("MemberViewModel", "currect picked member is ${member.value?.value?.hetekaId}")
              _member.value = memberRepository.getRandomMember()
            Log.i("MemberViewModel", "new picked member is ${member.value?.value?.hetekaId}")
        }
    }

//  fun likeParliamentMember() {
//  viewModelScope.launch {
//   memberRepository.likeParliamentMember(hetekaIdKey)
//   Log.i("MemberViewModel", "likeParliamentMember called")
//  }
// }

//    private var parliamentMember = MutableLiveData<ParliamentMember?>()
//
//    private val allMembers = database.getAllMembers()
//    val allMemberString = Transformations.map(allMembers) { allMembers ->
//        formatMembers(allMembers, application.resources)
//    }
//
////    private val _hetekaId = MutableLiveData<Int>()
////    val hetekaId: LiveData<Int>
////        get() = _hetekaId
//
//    private val _response = MutableLiveData<String>()
//    val response: LiveData<String>
//        get() = _response
//
//    private val _likes = MutableLiveData<Int>()
//    val likes: LiveData<Int>
//        get() = _likes
//
//    private val _fullName = MutableLiveData<String>()
//    val fullName: LiveData<String>
//        get() = _fullName
//
//    private val _firstName = MutableLiveData<String>()
//    val firstName: LiveData<String>
//        get() = _firstName
//
//    private val _lastName = MutableLiveData<String>()
//    val lastName: LiveData<String>
//        get() = _lastName
//
//    private val _party = MutableLiveData<String>()
//    val party: LiveData<String>
//        get() = _party
//
//    init {
//        initializeParliamentMember(hetekaId)
//        Log.i("MemberViewModel", "MemberViewModel created!")
//        Log.i("MemberViewModel", "Clicked members id is $hetekaId")
//    }
//
//    private fun initializeParliamentMember(hetekaId: Int) {
//        viewModelScope.launch {
//            try {
//                parliamentMember.value = getMemberById(hetekaId)
//                Log.i("MemberViewModel", parliamentMember.value!!.fullname)
//                _fullName.value = parliamentMember.value?.fullname
//                _party.value = parliamentMember.value?.party
//                _likes.value = 0
//            } catch (e: Exception) {
//                Log.e("MemberViewModel", "Member initialization failed", e)
//            }
//
//        }
//    }
//
//    private suspend fun getMemberById(hetekaId: Int): ParliamentMember? {
//        var clickedMember = database.getMemberById(hetekaId)
//        if (clickedMember?.firstname.isNullOrEmpty()) {
//            clickedMember = null
//        }
//        return clickedMember
//    }
//
//    private suspend fun getRandomMemberFromDatabase(): ParliamentMember? {
//        var randomMember = database.getRandomMember()
//        if (randomMember?.firstname.isNullOrEmpty()) {
//            randomMember = null
//        }
//        return randomMember
//    }
//
//    fun dislike() {
//        _likes.value = (_likes.value)?.minus(1)
//    }
//
//    fun like() {
//        _likes.value = (_likes.value)?.plus(1)
//    }

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