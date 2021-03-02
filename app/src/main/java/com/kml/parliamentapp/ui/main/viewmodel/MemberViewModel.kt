package com.kml.parliamentapp.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.kml.parliamentapp.data.model.Likes
import com.kml.parliamentapp.data.model.ParliamentMember
import com.kml.parliamentapp.data.repository.LikesRepository
import com.kml.parliamentapp.data.repository.MemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MemberViewModel(
    private val memberRepository: MemberRepository,
    private val likesRepository: LikesRepository,
    hetekaIdKey: Int
) : ViewModel() {

    // = memberRepository.getParliamentMemberWithId(hetekaIdKey)
    private var _member = MutableLiveData<LiveData<ParliamentMember>>()
    val member: LiveData<LiveData<ParliamentMember>>
    get() = _member

    private var _likes = MutableLiveData<LiveData<Likes>>()
    val likes: LiveData<LiveData<Likes>>
    get() = _likes

    init {
        _member.value = memberRepository.getParliamentMemberWithId(hetekaIdKey)
        viewModelScope.launch {
            _likes.value = likesRepository.getLikesForParliamentMember(hetekaIdKey)
        }
    }


    fun likeParliamentMember() {
        viewModelScope.launch {
            _member.value?.value?.let { likesRepository.likeParliamentMember(it.hetekaId) }
        }
    }
    fun dislikeParliamentMember() {
        viewModelScope.launch {
            _member.value?.value?.let { likesRepository.dislikeParliamentMember(it.hetekaId) }
        }
    }

//   fun randomMember() {
//       Log.i("MemberViewModel", "Currect picked members id is ${member.value?.value?.hetekaId}")
//       _member.value = memberRepository.getRandomMember()
//       Log.i("MemberViewModel", "getRandomMember executed")
//       viewModelScope.launch {
//           Log.i("MemberViewModel", "in viewmodelScope")
//           if(_member.value?.value?.hetekaId != null) {
//               Log.i("MemberViewModel", "ID wasn't null")
//               _member.value?.value?.let { getLikesNewMember(it.hetekaId) }
//           }else{
//               Log.i("MemberViewModel", "ID was null")
//           }
//       }
//       Log.i("MemberViewModel", "New picked members id is ${member.value?.value?.hetekaId}")

//       GlobalScope.launch(Dispatchers.IO) {
//           Log.i("MemberViewModel", "currect picked member is ${member.value?.value?.hetekaId}")
//           _member.value = memberRepository.getRandomMember()
//           delay(1000)
//           Log.i("MemberViewModel", "new picked member is ${member.value?.value?.hetekaId}")
//       }
//        viewModelScope.launch {
//            Log.i("MemberViewModel", "randomMember method launced")
//
//        }
//        viewModelScope.launch {
//
//            if(_member.value?.value?.hetekaId != null) {
//                _likes.value = likesRepository.getLikesForParliamentMember(_member.value!!.value!!.hetekaId)
//            }
//        }
//    }
//    suspend fun getLikesNewMember(id: Int) {
//        _likes.value = likesRepository.getLikesForParliamentMember(id)
//    }

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