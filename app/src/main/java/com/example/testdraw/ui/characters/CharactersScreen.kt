package com.example.testdraw.ui.characters

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testdraw.data.model.Character
import com.example.testdraw.ui.AppViewModelProvider

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharactersScreen(
    viewModel: CharactersScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.homeUiState.collectAsState()
    CharacterList(list = uiState)

//    Surface(modifier = Modifier.fillMaxSize()) {
//        Text(text = uiState)
//    }
}

@Composable
fun CharacterList(list: List<Character>) {
    LazyColumn {
        items(list) {
            CharacterCard(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCard(character: Character, modifier: Modifier = Modifier) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(text = character.name)
    }
}
