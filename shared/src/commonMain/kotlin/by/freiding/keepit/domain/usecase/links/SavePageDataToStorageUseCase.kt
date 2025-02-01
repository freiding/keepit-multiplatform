package by.freiding.keepit.domain.usecase.links

import by.freiding.keepit.domain.usecase.UseCase
import by.freiding.keepit.domain.usecase.UseCaseResult
import kotlinx.coroutines.flow.Flow

data class SavePageDataToStorageUseCaseParams(
    val url: String,
    val title: String?,
    val description: String?,
    val imageUrl: String?
)

class SavePageDataToStorageUseCase(): UseCase<SavePageDataToStorageUseCaseParams, UseCaseResult<Nothing>>() {

    override fun invoke(params: SavePageDataToStorageUseCaseParams): Flow<UseCaseResult<Nothing>> {
        TODO("Not yet implemented")
    }
}