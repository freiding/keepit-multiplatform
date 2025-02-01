package by.freiding.keepit.data.remote.parser

import by.freiding.keepit.data.remote.model.response.PageMetadata
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class KsoupMetadataParserTest {

    private val parser: MetadataParser = KsoupMetadataParser()

    @Test
    fun medium_article_metadata_parser_test() = runTest {
        val url = "https://medium.com/@adman.shadman/implementing-a-share-functionality-in-a-kotlin-multiplatform-kmp-android-ios-8f2bbf3e117f"
        val actualResult = parser.parse(url)
        val expectedResult = PageMetadata(
            title = "Implementing a Share Functionality in Kotlin Multiplatform (KMP) Android/IOS | by Shadman Adman | Medium",
            description = "The shared interface acts as a contract for platform-specific implementations. Here’s how it looks in the commonMain module: On Android, sharing content is handled using an Intent. The implementation…",
            imageUrl = "https://miro.medium.com/v2/resize:fit:412/1*l-RutjsYqTaY2AQA4dpV4Q.png"
        )
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun habr_article_metadata_parser_test() = runTest {
        val url = "https://habr.com/ru/articles/875460/"
        val actualResult = parser.parse(url)
        val expectedResult = PageMetadata(
            title = "Как синтаксический сахар Kotlin может сломать вам логику работы приложения",
            description = "При описании работы UI я использую концепцию «события», которое передаётся в логику UI слоя для дальнейшей обработки. Это позволяет мне не делать множество методов и с помощью...",
            imageUrl = "https://habrastorage.org/getpro/habr/upload_files/e8b/ef4/b83/e8bef4b83ff54853d85f7e488b28cf1d.gif"
        )

        assertEquals(expectedResult, actualResult)
    }
}