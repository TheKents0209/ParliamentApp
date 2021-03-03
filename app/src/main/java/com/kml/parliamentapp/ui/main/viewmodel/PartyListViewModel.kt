package com.kml.parliamentapp.ui.main.viewmodel

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* ViewModel gets list of parliament members from repo and provides that info for fragment
* */

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kml.parliamentapp.data.repository.MemberRepository

class PartyListViewModel(memberRepository: MemberRepository) : ViewModel() {

    val parties = memberRepository.getParties()

    private val _navigateToPartySelected = MutableLiveData<String>()
    val navigateToPartySelected: LiveData<String>
        get() = _navigateToPartySelected

    fun onPartyClicked(party: String) {
        _navigateToPartySelected.value = party
    }

    fun onNavigateToPartySelected() {
        _navigateToPartySelected.value = null
    }


}