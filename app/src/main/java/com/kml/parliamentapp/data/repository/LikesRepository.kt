package com.kml.parliamentapp.data.repository

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Repository that is linked to LikesDatabaseDao and includes methods for database search
* */

import androidx.lifecycle.LiveData
import com.kml.parliamentapp.data.database.LikesDatabaseDao
import com.kml.parliamentapp.data.model.Likes

class LikesRepository(private val databaseDao: LikesDatabaseDao) {

    //Returns current member like count, if that member doesn't have entry in database, creates a new entry with value 0
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