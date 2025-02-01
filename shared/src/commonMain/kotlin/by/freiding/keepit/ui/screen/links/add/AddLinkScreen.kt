package by.freiding.keepit.ui.screen.links.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import by.freiding.keepit.ui.Padding
import by.freiding.keepit.ui.components.ScreenWrapper
import by.freiding.keepit.ui.components.toolbar.AppToolbar
import by.freiding.keepit.ui.theme.colorScheme
import org.koin.compose.koinInject

@Composable
fun AddLinkScreen(
    navController: NavController,
    viewModel: AddLinkScreenViewModel = koinInject(),
) {
    val clipboard = LocalClipboardManager.current

    val url by viewModel.url.collectAsState()
    val pageDataState by viewModel.pageData.collectAsState()
    val savingState by viewModel.savingState.collectAsState()

    ScreenWrapper(screenName = "AddLink") {
        Scaffold(
            topBar = {
                AppToolbar(
                    title = "Add link",
                    onNavigationClick = { navController.navigateUp() }
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = url,
                    onValueChange = { viewModel.onUrlChanged(it) },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text(text = "https://domain.com/...")
                    },
                    label = {
                        Text(text = "Url")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Uri,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            viewModel.onUrlInputCompleted()
                        }
                    ),
                    suffix = {
                        Text(
                            text = "Paste",
                            color = colorScheme.colorPrimary,
                            modifier = Modifier.clickable {
                                if (clipboard.hasText()) {
                                    viewModel.onUrlChanged(clipboard.getText()?.text ?: "")
                                }
                            }
                        )
                    }
                )

                PagePreviewComponent(
                    modifier = Modifier.padding(vertical = Padding.S),
                    state = pageDataState
                )

                Button(
                    onClick = { viewModel.onSaveClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Save")
                }

            }

        }
    }
}

