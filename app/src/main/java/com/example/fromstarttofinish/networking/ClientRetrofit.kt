package com.example.fromstarttofinish.networking

import com.example.fromstarttofinish.networking.dto.JsonDataApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  ClientRetrofit{
    private val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    private val retrofit : Retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()

    fun getServiceApi() : JsonDataApi {
        return retrofit.create(JsonDataApi::class.java)
    }
}