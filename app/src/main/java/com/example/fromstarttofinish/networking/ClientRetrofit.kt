package com.example.fromstarttofinish.networking

import com.example.fromstarttofinish.networking.dto.FootballApiCall
import com.example.fromstarttofinish.networking.dto.countriDtoList
import com.example.fromstarttofinish.networking.dto.getCountryDto
import com.example.fromstarttofinish.usecases.model.footballApiModel
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthorizationInterceptor: Interceptor{
    override fun intercept(chain:Interceptor.Chain):Response {
        val request = chain.request()

        val newRequest = request.newBuilder().addHeader(
            "x-rapidapi-key",
            "vdtghftdh")
            .build()
        return chain.proceed(newRequest)
    }

}

class  ClientRetrofit{
    //https://jsonplaceholder.typicode.com/
    private val BASE_URL: String = "'https://v3.football.api-sports.io"
    private val retrofit : Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()
    private val  dataService = retrofit.create(FootballApiCall::class.java)

    // if it was use a model it shuld been map ex. .map{lambda}
    //suspend fun getServiceApiJsonFake(): List<FakeDataApiModel> = dataService.retrivePosts().map { it.getFakeDataItem() }
    suspend fun getServiceFootballCountry():List<footballApiModel> = dataService.retriveCountry().map { it.getCountryDto() }

}