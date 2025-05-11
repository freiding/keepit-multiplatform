package by.freiding.keepit.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.freiding.keepit.ui.components.ScreenWrapper
import by.freiding.keepit.ui.components.bottomnavbar.BottomNavigationBar
import by.freiding.keepit.ui.components.bottomnavbar.NavigationItem
import by.freiding.keepit.ui.navigation.Routes
import by.freiding.keepit.ui.screen.links.list.StoredLinksListScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel = koinViewModel(),
) {
    val bottomBarNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                BottomNavigationBar(navController = bottomBarNavController)
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomBarNavigator(
                bottomBarNavController = bottomBarNavController,
                mainNavController = navController,
            )
        }
    }
}

@Composable
private fun BottomBarNavigator(
    bottomBarNavController: NavHostController,
    mainNavController: NavHostController,
) {
    NavHost(bottomBarNavController, startDestination = NavigationItem.Links.route) {
        composable<Routes.LinksList> {
            StoredLinksListScreen(
                navController = mainNavController,
            )
        }
        composable<Routes.NotesList> {
            Box(modifier = Modifier)
        }
        composable<Routes.Settings> {
            Box(modifier = Modifier)
        }
    }
}
