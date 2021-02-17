package com.kml.parliamentapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ParliamentMember::class], version = 1, exportSchema = false)
abstract class MembersDatabase : RoomDatabase() {

    abstract val membersDatabaseDao: MembersDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: MembersDatabase? = null

        fun getInstance(context: Context): MembersDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MembersDatabase::class.java,
                        "parliament_member_database"
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