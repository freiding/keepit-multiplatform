package by.freiding.keepit.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(tableName = "page_data")
data class PageDataEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val categoryId: String? = null,
    val title: String?,
    val description: String?,
    val imageUrl: String,
    val createdAt: Instant = Clock.System.now(),
    val updatedAt: Instant = Clock.System.now(),
    val deletedAt: Instant? = null
): BaseEntity