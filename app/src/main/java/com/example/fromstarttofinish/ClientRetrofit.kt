package com.example.fromstarttofinish

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DataApi{

    @GET("posts/")
    suspend fun retrivePosts(): FakeData
}

object  ClientRetrofit{
    private val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    private val retrofit : Retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()

    fun getServiceApi() : DataApi {
        return retrofit.create(DataApi::class.java)
    }
}