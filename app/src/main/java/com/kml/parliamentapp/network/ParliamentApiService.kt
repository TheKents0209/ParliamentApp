package com.kml.parliamentapp.network

import com.kml.parliamentapp.database.ParliamentMember
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//TODO: Use https://avoindata.eduskunta.fi/api/v1/seating/ instead.
private const val BASE_URL =
    "http://users.metropolia.fi/~kenertml/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ParliamentApiService {
    @GET("eduskunta")
    suspend fun getMembers():
            List<ParliamentMember>
}

object ParliamentApi {
    val retrofitService: ParliamentApiService by lazy {
        retrofit.create(ParliamentApiService::class.java)
    }
}