package com.example.testdraw.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.testdraw.NarutoApplication
import com.example.testdraw.ui.characters.CharactersScreenViewModel

object AppViewModelProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val application = (this[APPLICATION_KEY] as NarutoApplication)
            val narutoRepository = application.container.narutoRepository
            CharactersScreenViewModel(narutoRepository = narutoRepository)
        }
    }
}