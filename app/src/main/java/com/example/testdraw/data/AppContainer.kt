package com.example.testdraw.data

import com.example.testdraw.network.NarutoApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import retrofit2.Retrofit


interface AppContainer {
    val narutoRepository: NarutoRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://narutodb.xyz/api/"

    private val json: Json = Json {
        ignoreUnknownKeys = true
        allowStructuredMapKeys = true
    }

    private val contentType = "application/json".toMediaType()
    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(json.asConverterFactory(contentType))
        .baseUrl(baseUrl)
        .build()

    private val narutoService: NarutoApiService by lazy {
        retrofit.create(NarutoApiService::class.java)
    }

    override val narutoRepository: NarutoRepository by lazy {
        NetworkNarutoRepository(narutoService)
    }
}