package by.freiding.keepit.domain.usecase.links

import by.freiding.keepit.data.remote.parser.MetadataParser
import by.freiding.keepit.domain.model.PageData
import by.freiding.keepit.domain.usecase.UseCase
import by.freiding.keepit.domain.usecase.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

data class FetchPageDataByLinkUseCaseParams(
    val url: String,
)
class FetchPageDataByLinkUseCase(
    private val parser: MetadataParser
): UseCase<FetchPageDataByLinkUseCaseParams, UseCaseResult<PageData>>() {

    override fun invoke(params: FetchPageDataByLinkUseCaseParams): Flow<UseCaseResult<PageData>> = flow {
        emit(UseCaseResult.loading())
        val parseResult = parser.parse(params.url)
        val pageData = PageData(
            url = params.url,
            title = parseResult.title,
            description = parseResult.description,
            imageUrl = parseResult.imageUrl,
        )
        emit(UseCaseResult.success(pageData))
        return@flow
    }.catch { error ->
        emit(UseCaseResult.failure(error))
        return@catch
    }
}