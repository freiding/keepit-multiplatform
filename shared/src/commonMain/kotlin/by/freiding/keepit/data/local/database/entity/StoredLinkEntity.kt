package by.freiding.keepit.data.local.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(
    tableName = "links",
    indices = [Index(value = ["url"], unique = true)]
)
data class StoredLinkEntity(
    @PrimaryKey
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
): BaseEntity