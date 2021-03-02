package com.kml.parliamentapp.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kml.parliamentapp.data.database.MemberDatabaseDao
import com.kml.parliamentapp.data.repository.MemberRepository
import com.kml.parliamentapp.ui.main.viewmodel.MemberViewModel

class MemberViewModelFactory(
    private val databaseDao: MemberDatabaseDao,
    private val application: Application,
    private val hetekaIdKey: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberViewModel::class.java)) {
            return MemberViewModel(MemberRepository(databaseDao), hetekaIdKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}