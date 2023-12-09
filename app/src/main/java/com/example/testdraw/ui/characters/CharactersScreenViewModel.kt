package com.example.testdraw.ui.characters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdraw.data.NarutoRepository
import com.example.testdraw.data.model.Character
import com.example.testdraw.data.model.Characters
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class CharUiState(val page: Int) {
    data class Success(val currPage: Int, val characters: List<Character>) : CharUiState(currPage)
    data class Error(val currPage: Int) : CharUiState(currPage)
    data class Loading(val currPage: Int) : CharUiState(currPage)
}

class CharactersScreenViewModel(
    savedStateHandle: SavedStateHandle,
    narutoRepository: NarutoRepository): ViewModel() {

    private val limit = 20
    private val pageNumb: Int = checkNotNull(savedStateHandle[CharactersScreenDest.pageNumbArg])

    val homeUiState: StateFlow<CharUiState> =
            narutoRepository.getAllCharacters(pageNumb, limit)
                .map { it?.toSuccess() ?: CharUiState.Error(pageNumb) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = CharUiState.Loading(pageNumb)
                )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class CharactersUiState (
    val page: Int = 0,
    val characters: List<Character> = listOf()
)

fun Characters.toSuccess() = CharUiState.Success(this.currentPage, this.characters)