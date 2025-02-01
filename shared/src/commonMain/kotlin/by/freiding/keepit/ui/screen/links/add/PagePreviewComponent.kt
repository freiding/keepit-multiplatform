package by.freiding.keepit.ui.screen.links.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import by.freiding.keepit.domain.model.PageData
import by.freiding.keepit.domain.model.RequestResultState
import by.freiding.keepit.ui.FontSize
import by.freiding.keepit.ui.Padding
import by.freiding.keepit.ui.theme.colorScheme
import coil3.compose.AsyncImage

private val containerModifier: Modifier
    get() {
        return Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(Padding.L.div(2))
    }

@Composable
fun PagePreviewComponent(
    modifier: Modifier = Modifier,
    state: RequestResultState<PageData>,
) {
    when (state) {
        is RequestResultState.None -> {}
        is RequestResultState.Loading -> {
            LoadingComponent(modifier)
        }

        is RequestResultState.Success -> {
            SuccessComponent(modifier, state.data)
        }

        is RequestResultState.Failure -> {}
    }
}

@Composable
private fun SuccessComponent(
    modifier: Modifier = Modifier,
    pageData: PageData,
) {
    Row(modifier = modifier.then(containerModifier)) {

        AsyncImage(
            model = pageData.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .clip(RoundedCornerShape(4.dp)),
            onError = { error ->
                error.result.throwable.printStackTrace()
            },
        )

        Column(
            modifier = Modifier.padding(start = Padding.S)
        ) {
            Text(
                text = pageData.title ?: "Title",
                fontSize = FontSize.S,
                lineHeight = FontSize.S,
                color = colorScheme.primaryText
            )
            Text(
                text = pageData.description ?: "",
                fontSize = FontSize.XS,
                lineHeight = FontSize.XS,
                color = colorScheme.secondaryText,
                modifier = Modifier.padding(top = Padding.XS)
            )
        }
    }
}

@Composable
private fun LoadingComponent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.then(containerModifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .padding(Padding.M)
                .size(32.dp)
        )
    }

}