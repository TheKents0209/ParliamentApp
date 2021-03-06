package com.kml.parliamentapp

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Overrides Applications onCreate to call custom method that setups worker
* */

import android.app.Application
import androidx.work.*
import com.kml.parliamentapp.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ParliamentApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

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
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS).setConstraints(constraints).build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.RefreshDatabase,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}