package by.freiding.keepit.data.remote.parser

import by.freiding.keepit.data.remote.model.response.PageMetadata
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.model.MetaData
import com.fleeksoft.ksoup.network.parseGetRequest
import com.fleeksoft.ksoup.nodes.Document


class KsoupMetadataParser(): MetadataParser {

    override suspend fun parse(url: String): PageMetadata {
        val doc: Document = Ksoup.parseGetRequest(url = url) // suspend function
        val metadata: MetaData = Ksoup.parseMetaData(element = doc) // suspend function
        val title = formatString(metadata.title ?: metadata.ogTitle)
        val description = formatString(metadata.description ?: metadata.ogDescription)
        val imageUrl = metadata.ogImage

        return PageMetadata(title, description, imageUrl)
    }

    private fun formatString(input: String?): String? {
        return input?.replace(" ", " ")
    }
}