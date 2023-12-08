package com.example.testdraw.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdraw.data.NarutoRepository
import com.example.testdraw.data.model.Character
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CharactersScreenViewModel(narutoRepository: NarutoRepository): ViewModel() {

    val homeUiState: StateFlow<List<Character>> =
//    val homeUiState: StateFlow<String> =
        narutoRepository.getAllCharacters()
//            .map {  }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = listOf()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}



