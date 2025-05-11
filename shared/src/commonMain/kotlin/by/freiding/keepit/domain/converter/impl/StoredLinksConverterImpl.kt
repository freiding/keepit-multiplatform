package by.freiding.keepit.domain.converter.impl

import by.freiding.keepit.data.local.database.entity.StoredLinkEntity
import by.freiding.keepit.domain.converter.StoredLinksConverter
import by.freiding.keepit.domain.model.StoredLinkData

class StoredLinksConverterImpl: StoredLinksConverter {
    override fun toStoredPageData(obj: StoredLinkEntity): StoredLinkData {
        return StoredLinkData(
            id = obj.id,
            url = obj.url,
            categoryId = obj.categoryId,
            title = obj.title,
            description = obj.description,
            imageUrl = obj.imageUrl,
            isRead = obj.isRead,
            createdAt = obj.createdAt,
            updatedAt = obj.updatedAt,
            deletedAt = obj.deletedAt
        )
    }
}