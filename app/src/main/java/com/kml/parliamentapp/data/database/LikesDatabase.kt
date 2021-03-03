package com.kml.parliamentapp.data.database

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Builds database for Likes class
* */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kml.parliamentapp.data.model.Likes
import com.kml.parliamentapp.data.model.ParliamentMember

@Database(entities = [Likes::class], version = 1, exportSchema = false)
abstract class LikesDatabase : RoomDatabase() {

    abstract val likesDatabaseDao: LikesDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: LikesDatabase? = null

        fun getInstance(context: Context): LikesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LikesDatabase::class.java,
                        "likes_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}