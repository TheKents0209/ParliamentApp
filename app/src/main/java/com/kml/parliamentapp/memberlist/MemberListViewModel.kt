package com.kml.parliamentapp.memberlist

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kml.parliamentapp.database.MembersDatabaseDao
import com.kml.parliamentapp.database.ParliamentMember
import com.kml.parliamentapp.formatMembers
import javax.sql.DataSource

class MemberListViewModel(
    dataSource: MembersDatabaseDao,
    application: Application
) : ViewModel() {

    val database = dataSource

    private var selectedMember = MutableLiveData<ParliamentMember?>()

    val allMembers = database.getAllMembers()

    val allMemberString = Transformations.map(allMembers) { allMembers ->
        formatMembers(allMembers, application.resources)
    }
}