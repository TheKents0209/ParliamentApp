package com.kml.parliamentapp.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kml.parliamentapp.data.database.MemberDatabaseDao
import com.kml.parliamentapp.data.repository.MemberRepository
import com.kml.parliamentapp.ui.main.viewmodel.PartyListViewModel

@Suppress("UNCHECKED_CAST")
class PartyListViewModelFactory(
    private val databaseDao: MemberDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PartyListViewModel::class.java)) {
            return PartyListViewModel(MemberRepository(databaseDao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}