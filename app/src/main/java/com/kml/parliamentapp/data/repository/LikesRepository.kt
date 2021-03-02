package com.kml.parliamentapp.data.repository

import androidx.lifecycle.LiveData
import com.kml.parliamentapp.data.api.ParliamentApi
import com.kml.parliamentapp.data.database.LikesDatabaseDao
import com.kml.parliamentapp.data.model.Likes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LikesRepository(private val databaseDao: LikesDatabaseDao) {

    suspend fun getLikesForParliamentMember(id: Int): LiveData<Likes> {
        var memberLikes = databaseDao.getLikes(id)
        if(memberLikes.value?.hetekaId == null) {
            databaseDao.insert(Likes(id))
            memberLikes = databaseDao.getLikes(id)
        }
        return memberLikes
    }
    suspend fun likeParliamentMember(id: Int) {
        databaseDao.likeMember(id)
    }
    suspend fun dislikeParliamentMember(id: Int) {
        databaseDao.dislikeMember(id)
    }
}