package com.kml.parliamentapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kml.parliamentapp.data.model.ParliamentMember

@Database(entities = [ParliamentMember::class], version = 1, exportSchema = false)
abstract class MemberDatabase : RoomDatabase() {

    abstract val membersDatabaseDao: MemberDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: MemberDatabase? = null

        fun getInstance(context: Context): MemberDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemberDatabase::class.java,
                        "parliament_member_table"
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