package com.kml.parliamentapp.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kml.parliamentapp.network.ParliamentApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

class JsonToDatabase(
    val database: MembersDatabaseDao
) {

    fun convert() {
        GlobalScope.launch {
            try {
                val listResult = ParliamentApi.retrofitService.getMembers()

                database.insertAll(listResult)
                Log.i("JsonToDatabase", "Success ${listResult.size} inserted")
            } catch (e: Exception) {
                Log.e("JsonToDatabase", "Error", e)
            }
        }
    }
}

