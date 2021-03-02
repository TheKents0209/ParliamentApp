package com.kml.parliamentapp

import android.app.Application
import android.util.Log
import androidx.work.*
import com.kml.parliamentapp.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ParliamentApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)
    private val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()


    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        Log.i("ParliamentApplication", "setupRecurringWork run")

        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(15, TimeUnit.MINUTES).setConstraints(constraints).build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.RefreshDatabase,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }

    private fun setupDatabaseInit() {
        Log.i("ParliamentApplication", "setupDatabaseInit run")

        val buildRequest = OneTimeWorkRequestBuilder<RefreshDataWorker>().setConstraints(constraints).build()

        WorkManager.getInstance().enqueueUniqueWork(RefreshDataWorker.RefreshDatabase,
        ExistingWorkPolicy.REPLACE, buildRequest)
    }
}