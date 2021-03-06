package com.kml.parliamentapp.ui.base

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Creates new ViewModel for MemberFragment
* */

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kml.parliamentapp.data.database.LikesDatabaseDao
import com.kml.parliamentapp.data.database.MemberDatabaseDao
import com.kml.parliamentapp.data.repository.LikesRepository
import com.kml.parliamentapp.data.repository.MemberRepository
import com.kml.parliamentapp.ui.main.viewmodel.MemberViewModel

class MemberViewModelFactory(
    private val databaseDao: MemberDatabaseDao,
    private val likesDatabaseDao: LikesDatabaseDao,
    private val hetekaIdKey: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberViewModel::class.java)) {
            return MemberViewModel(MemberRepository(databaseDao),LikesRepository(likesDatabaseDao), hetekaIdKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}