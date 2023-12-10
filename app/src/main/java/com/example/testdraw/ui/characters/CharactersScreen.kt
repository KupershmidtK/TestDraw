package com.example.testdraw.ui.characters

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testdraw.R
import com.example.testdraw.data.model.Character
import com.example.testdraw.ui.AppViewModelProvider
import com.example.testdraw.ui.navigation.NavigationDestination

object CharactersScreenDest: NavigationDestination {
    override val route = "characters"
    val pageNumbArg = "pageNumb"
    val routeWithArgs = "$route/{$pageNumbArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharactersScreen(
    navigateHome: () -> Unit,
    navigateNext: (Int) -> Unit,
    navigatePrev: (Int) -> Unit,
    viewModel: CharactersScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState by viewModel.homeUiState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        //modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { CharacterListTopBar(uiState.page) },
        bottomBar = { CharacterBottomNavBar(
            navigateHome = navigateHome,
            navigateNext = { navigateNext(uiState.page + 1) },
            navigatePrev = {}
        ) }
    ) {
        when (uiState) {
            is CharUiState.Loading -> LoadingScreen()
            is CharUiState.Error -> ErrorScreen(navigateHome = navigateHome)
            is CharUiState.Success -> {
                val successState = uiState as CharUiState.Success
                CharacterList(list = successState.characters)
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()){
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading),
            alignment = Alignment.Center
        )
    }

}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(navigateHome: () -> Unit, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
            )
            Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterList(
    list: List<Character>,
    modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier) {
        items(list) {
            CharacterCard(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCard(
    character: Character, modifier:
    Modifier = Modifier) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(text = character.name)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListTopBar(page: Int) {
    return CenterAlignedTopAppBar(title = { Text(stringResource(R.string.page_numb, page)) })
}

data class BottomNavItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterBottomNavBar(
    navigateHome: () -> Unit,
    navigatePrev: () -> Unit,
    navigateNext: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = { Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null) },
            label = { Text(stringResource(R.string.prev)) }
        )
        NavigationBarItem(
            selected = true,
            onClick = navigateHome,
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.home)) }
        )
        NavigationBarItem(
            selected = true,
            onClick = navigateNext,
            icon = { Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null) },
            label = { Text(stringResource(R.string.next)) }
        )
    }
}
