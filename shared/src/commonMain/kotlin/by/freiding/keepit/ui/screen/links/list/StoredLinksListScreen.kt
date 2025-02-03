package by.freiding.keepit.ui.screen.links.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import by.freiding.keepit.ui.Padding
import by.freiding.keepit.ui.components.ScreenWrapper
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StoredLinksListScreen(
    navController: NavController,
    viewModel: StoredLinksListScreenViewModel = koinViewModel()
) {

    val listState = rememberLazyListState()
    val storedLinkItems by viewModel.links.collectAsState()

    ScreenWrapper(
        screenName = "StoredLinksList"
    ) {
        LazyColumn(
            modifier = Modifier,
            state = listState
        ) {
            items(storedLinkItems) { item ->
                StoredLinksListItem(
                    modifier = Modifier.padding(horizontal = Padding.M, vertical = Padding.XS),
                    data = item
                )
            }
        }
    }
}