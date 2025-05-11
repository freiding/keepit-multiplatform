package by.freiding.keepit.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object MainScreen: Routes()

    @Serializable
    data object AddLink: Routes()


    @Serializable
    data object LinksList: Routes()


    @Serializable
    data object NotesList: Routes()


    @Serializable
    data object Settings: Routes()

}
