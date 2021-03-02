package com.kml.parliamentapp.ui.main.viewmodel

import androidx.lifecycle.*
import com.kml.parliamentapp.data.model.ParliamentMember
import com.kml.parliamentapp.data.repository.MemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MemberListViewModel(private val memberRepository: MemberRepository, party: String) : ViewModel() {

    private val _parliamentMemberList = MutableLiveData<LiveData<List<ParliamentMember>>>()
    val parliamentMemberList: LiveData<LiveData<List<ParliamentMember>>>
    get() = _parliamentMemberList

    private val _navigateToParliamentMemberDetails = MutableLiveData<Int>()
    val navigateToParliamentMemberDetails: LiveData<Int>
        get() = _navigateToParliamentMemberDetails

//    private val _selectedParliamentMemberDetails = MutableLiveData<LiveData<ParliamentMember>>()
//    val selectedParliamentMemberDetails: LiveData<LiveData<ParliamentMember>>
//    get() = _selectedParliamentMemberDetails

//    lateinit var selectedMember: LiveData<ParliamentMember>

    private var _networkError = MutableLiveData<Boolean>(false)
    val networkError: LiveData<Boolean>
    get() = _networkError

    private var _isNetworkErrorShown =  MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
    get() = _isNetworkErrorShown



    val parliamentMembers = memberRepository.getParliamentMembersByParty(party)

    private fun fetchParliamentMemberByParty(party: String) {
        viewModelScope.launch {
            _parliamentMemberList.value = memberRepository.getParliamentMembersByParty(party)
        }
    }

    fun onParliamentMemberClicked(id: Int) {
        _navigateToParliamentMemberDetails.value = id
        viewModelScope.launch {
            //_selectedParliamentMemberDetails.value = memberRepository.getParliamentMemberWithId(id)
//            selectedMember = memberRepository.getParliamentMemberWithId(id)
        }
    }

    fun onParliamentMemberDetailsNavigated() {
        _navigateToParliamentMemberDetails.value = null
    }


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}