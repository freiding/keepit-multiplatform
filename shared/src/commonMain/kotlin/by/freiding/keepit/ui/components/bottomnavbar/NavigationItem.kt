package by.freiding.keepit.ui.components.bottomnavbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import by.freiding.keepit.ui.navigation.Routes

sealed class NavigationItem(val route: Routes, val icon: ImageVector?, var title: String) {
    data object Links: NavigationItem(Routes.LinksList, Icons.Default.Home, "Links")
    data object Notes: NavigationItem(Routes.NotesList, Icons.Default.Notifications, "Notes")
    data object Settings: NavigationItem(Routes.Settings, Icons.Default.Settings, "Settings")
}
