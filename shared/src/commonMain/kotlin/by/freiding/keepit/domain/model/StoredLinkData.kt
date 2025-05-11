package by.freiding.keepit.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class StoredLinkData(
    val id: String,
    val url: String,
    val categoryId: String? = null,
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    val isRead: Boolean,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = Clock.System.now(),
    val deletedAt: Instant? = null
)