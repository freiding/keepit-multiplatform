package by.freiding.keepit.ui.navigation.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import by.freiding.keepit.ui.navigation.Routes

@Composable
fun MainNavigator(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.MainScreen) {
        composable<Routes.MainScreen> {  }

    }
}