package by.freiding.keepit.domain.converter

import by.freiding.keepit.data.local.database.entity.StoredLinkEntity
import by.freiding.keepit.domain.model.StoredLinkData

interface StoredLinksConverter {
    fun toStoredPageData(obj: StoredLinkEntity): StoredLinkData
}