package com.example.testdraw.data

import android.util.Log
import com.example.testdraw.data.model.Character
import com.example.testdraw.data.model.Characters
import com.example.testdraw.network.NarutoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow


interface NarutoRepository {
    fun getAllCharacters(page: Int, limit: Int): Flow<Characters?>
}

class NetworkNarutoRepository(
    private val narutoApiService: NarutoApiService
) : NarutoRepository {

    override fun getAllCharacters(page: Int, limit: Int): Flow<Characters?> = flow {
           val characters = try {
                narutoApiService.getAllCharacters(page, limit)
            } catch (e: Exception) {
                Log.e("NARUTO", Log.getStackTraceString(e));
                null
            }
            emit(characters)
        }
}
