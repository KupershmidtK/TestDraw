package com.example.testdraw.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testdraw.ui.characters.CharactersScreen
import com.example.testdraw.ui.characters.CharactersScreenDest
import com.example.testdraw.ui.home.HomeScreen
import com.example.testdraw.ui.home.HomeScreenDest

interface NavigationDestination {
    val route: String
}
@Composable
fun NarutoNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost (
        navController = navController,
        startDestination = HomeScreenDest.route,
        modifier = modifier
    ){
        composable(route = HomeScreenDest.route) {
            HomeScreen(
                charactersOnClick = { navController.navigate(route = "${CharactersScreenDest.route}/1") }
            )
        }
        composable(
            route = CharactersScreenDest.routeWithArgs,
            arguments = listOf(navArgument(CharactersScreenDest.pageNumbArg){ type = NavType.IntType })
            ) {
            CharactersScreen(
                navigateHome = { navController.navigate(HomeScreenDest.route) },
                navigateNext = { navController.navigate("${CharactersScreenDest.route}/$it") },
                navigatePrev = {}
            )
        }
    }
}