package com.example.testdraw.network

import retrofit2.http.GET
import com.example.testdraw.data.model.Characters
import retrofit2.http.Query

interface NarutoApiService {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int, @Query("limit") limit: Int): Characters
//    suspend fun getAllCharacters(): String
}