package com.example.testdraw.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.testdraw.NarutoApplication
import com.example.testdraw.ui.characters.CharactersScreenViewModel

object AppViewModelProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            //val application = (this[APPLICATION_KEY] as NarutoApplication)
            val narutoRepository = narutoApplication().container.narutoRepository
            CharactersScreenViewModel(
                this.createSavedStateHandle(),
                narutoRepository = narutoRepository)
        }
    }
}

fun CreationExtras.narutoApplication(): NarutoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as NarutoApplication)