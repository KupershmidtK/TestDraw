package com.example.testdraw.network

import retrofit2.http.GET
import com.example.testdraw.data.model.Characters

interface NarutoApiService {

    @GET("character")
    suspend fun getAllCharacters(): Characters
//    suspend fun getAllCharacters(): String
}