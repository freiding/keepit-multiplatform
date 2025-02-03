package by.freiding.keepit.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object MainScreen

    @Serializable
    data object AddLink

    @Serializable
    data object LinksList
}