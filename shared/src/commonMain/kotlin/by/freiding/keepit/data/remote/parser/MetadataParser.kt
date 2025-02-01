package by.freiding.keepit.data.remote.parser

import by.freiding.keepit.data.remote.model.response.PageMetadata

interface MetadataParser {

    suspend fun parse(url: String): PageMetadata
}