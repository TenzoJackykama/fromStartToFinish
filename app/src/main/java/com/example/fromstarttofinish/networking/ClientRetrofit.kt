package com.example.fromstarttofinish.networking

import com.example.fromstarttofinish.networking.dto.FakeData
import com.example.fromstarttofinish.networking.dto.JsonDataApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class  ClientRetrofit{
    private val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    private val retrofit : Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()
    private val  JsonDataService = retrofit.create(JsonDataApi::class.java)

    // if it was use a model it shuld been map ex. .map{lambda}
    suspend fun getServiceApi():FakeData = JsonDataService.retrivePosts()
}