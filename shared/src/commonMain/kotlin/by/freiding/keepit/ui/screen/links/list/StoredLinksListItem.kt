package by.freiding.keepit.ui.screen.links.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.freiding.keepit.domain.model.StoredLinkData
import by.freiding.keepit.ui.FontSize
import by.freiding.keepit.ui.Padding
import by.freiding.keepit.ui.theme.colorScheme
import by.freiding.keepit.utils.openUrl
import coil3.compose.AsyncImage

@Composable
fun StoredLinksListItem(
    modifier: Modifier = Modifier,
    data: StoredLinkData
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable {
                openUrl(data.url)
            }
            .clip(RoundedCornerShape(8.dp))
    ) {
        AsyncImage(
            model = data.imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.padding(horizontal = Padding.M, vertical = Padding.M)
        ) {
            Text(
                text = data.title ?: "Title",
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight.W500,
                color = colorScheme.primaryText
            )

            Text(
                text = data.description ?: "",
                fontSize = 13.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.W400,
                color = colorScheme.secondaryText,
                modifier = Modifier.padding(top = Padding.XS)
            )
        }
    }
}