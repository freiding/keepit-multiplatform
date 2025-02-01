package by.freiding.keepit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScreenWrapper(
    modifier: Modifier = Modifier,
    screenName: String,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        content()
    }
}