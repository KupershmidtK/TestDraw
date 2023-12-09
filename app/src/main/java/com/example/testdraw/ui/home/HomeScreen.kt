package com.example.testdraw.ui.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testdraw.ui.navigation.NavigationDestination

object HomeScreenDest: NavigationDestination {
    override val route = "home"
}
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    charactersOnClick: () -> Unit
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            val localModifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(2.dp, Color.Blue)
                .weight(1f)
            Box(
                modifier = localModifier.clickable(onClick = charactersOnClick),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Characters", fontSize = 32.sp)
            }
            Box(
                modifier = localModifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Clans", fontSize = 32.sp)
            }
            Box(
                modifier = localModifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Whooop", fontSize = 32.sp)
            }
        }
    }
}