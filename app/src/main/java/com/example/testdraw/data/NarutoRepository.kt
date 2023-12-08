package com.example.testdraw.data

import com.example.testdraw.data.model.Character
import com.example.testdraw.network.NarutoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow


interface NarutoRepository {
    fun getAllCharacters(): Flow<List<Character>>
//    fun getAllCharacters(): Flow<String>
}

class NetworkNarutoRepository(
    private val narutoApiService: NarutoApiService
) : NarutoRepository {

    override fun getAllCharacters(): Flow<List<Character>>
//    override fun getAllCharacters(): Flow<String>
        = flow { emit(narutoApiService.getAllCharacters().characters) }
}
