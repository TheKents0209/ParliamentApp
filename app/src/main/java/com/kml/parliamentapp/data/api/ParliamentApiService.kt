package com.kml.parliamentapp.data.api

/*
* 03.03.2021
* Kenert Lauri
* 2008815
* Builds moshi and retrofit, gets JSON data from URL provided and converts it to list that has ParliamentMember objects
* */

import com.kml.parliamentapp.data.model.ParliamentMember
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://avoindata.eduskunta.fi/api/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface ParliamentApiService {
    @GET("seating/")
    suspend fun getMembers(): List<ParliamentMember>
}

object ParliamentApi {
    val members: ParliamentApiService = retrofit.create(ParliamentApiService::class.java)
}

enum class ParliamentApiStatus {LOADING, DONE}