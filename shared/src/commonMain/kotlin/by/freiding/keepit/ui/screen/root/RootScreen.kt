package by.freiding.keepit.ui.screen.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import by.freiding.keepit.ui.navigation.navigator.MainNavigator
import by.freiding.keepit.ui.theme.ApplicationTheme
import org.koin.compose.koinInject

@Composable
fun RootScreen(
    viewModel: RootViewModel = koinInject()
) {
    val navController = rememberNavController()

    ApplicationTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            MainNavigator(navController)
        }
    }

}