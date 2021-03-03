package com.kml.parliamentapp.ui.base

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Creates new ViewModel for MemberListFragment
* */

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kml.parliamentapp.data.database.MemberDatabaseDao
import com.kml.parliamentapp.data.repository.MemberRepository
import com.kml.parliamentapp.ui.main.viewmodel.MemberListViewModel

@Suppress("UNCHECKED_CAST")
class MemberListViewModelFactory(
    private val databaseDao: MemberDatabaseDao,
    private val application: Application,
    private val party: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberListViewModel::class.java)) {
            return MemberListViewModel(MemberRepository(databaseDao),party) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}