package com.example.fromstarttofinish.networking.dto

import retrofit2.http.GET

interface JsonDataApi {
    @GET("posts/")
    suspend fun retrivePosts(): FakeData
}