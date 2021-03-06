package com.kml.parliamentapp.ui.main.viewmodel

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* ViewModel gets list of parliament members from repo and provides that info for fragment
* */

import androidx.lifecycle.*
import com.kml.parliamentapp.data.model.Likes
import com.kml.parliamentapp.data.model.ParliamentMember
import com.kml.parliamentapp.data.repository.LikesRepository
import com.kml.parliamentapp.data.repository.MemberRepository
import kotlinx.coroutines.launch

class MemberViewModel(memberRepository: MemberRepository, private val likesRepository: LikesRepository, hetekaIdKey: Int
) : ViewModel() {

    private var _member = MutableLiveData<LiveData<ParliamentMember>>()
    val member: LiveData<LiveData<ParliamentMember>>
    get() = _member

    //Current members likes
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
}