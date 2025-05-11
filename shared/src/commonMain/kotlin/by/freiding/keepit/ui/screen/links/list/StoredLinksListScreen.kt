package by.freiding.keepit.ui.screen.links.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import by.freiding.keepit.ui.CornerRadius
import by.freiding.keepit.ui.Padding
import by.freiding.keepit.ui.components.ScreenWrapper
import by.freiding.keepit.ui.navigation.Routes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StoredLinksListScreen(
    navController: NavController,
    viewModel: StoredLinksListScreenViewModel = koinViewModel()
) {

    val listState = rememberLazyListState()
    val storedLinkItems by viewModel.links.collectAsState()

    ScreenWrapper(
        screenName = "StoredLinksList",
    ) {

        Box(modifier = Modifier) {
            LazyColumn(
                modifier = Modifier,
                state = listState,
            ) {
                items(storedLinkItems) { item ->
                    StoredLinksListItem(
                        modifier = Modifier.padding(horizontal = Padding.M, vertical = Padding.XS),
                        data = item,
                    )
                }
            }
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.AddLink)
                },
                shape = RoundedCornerShape(CornerRadius.S),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Padding.M)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add link")
            }
        }

    }
}
