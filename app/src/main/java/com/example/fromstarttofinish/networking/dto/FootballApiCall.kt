package com.example.fromstarttofinish.networking.dto

import retrofit2.http.GET

interface FootballApiCall{
    @GET("countries/")
    suspend fun retriveCountry(): countriDtoList
}