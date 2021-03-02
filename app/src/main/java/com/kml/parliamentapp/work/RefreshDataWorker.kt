package com.kml.parliamentapp.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kml.parliamentapp.data.database.MemberDatabase
import com.kml.parliamentapp.data.database.MemberDatabase.Companion.getInstance
import com.kml.parliamentapp.data.database.MemberDatabaseDao
import com.kml.parliamentapp.data.repository.MemberRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    companion object {
        const val RefreshDatabase = "com.kml.parliamentapp.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getInstance(applicationContext)
        val repository = MemberRepository(database.membersDatabaseDao)

        try {
            repository.refreshParliamentMembers()
            Log.i("RefreshDataWorker", "Work request for sync is run")
        } catch (e:HttpException) {
            return Result.retry()
        }

        return Result.success()
    }

}