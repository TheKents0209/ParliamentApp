package com.kml.parliamentapp.ui.main.viewmodel

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* ViewModel gets list of parliament members from repo and provides that info for fragment
* */

import androidx.lifecycle.*
import com.kml.parliamentapp.data.repository.MemberRepository

class MemberListViewModel(memberRepository: MemberRepository, party: String) : ViewModel() {

    private val _navigateToParliamentMemberDetails = MutableLiveData<Int>()
    val navigateToParliamentMemberDetails: LiveData<Int>
        get() = _navigateToParliamentMemberDetails

    //Gets list of members that are in asked party
    val parliamentMembers = memberRepository.getParliamentMembersByParty(party)


    fun onParliamentMemberClicked(id: Int) {
        _navigateToParliamentMemberDetails.value = id
    }

    fun onParliamentMemberDetailsNavigated() {
        _navigateToParliamentMemberDetails.value = null
    }
}