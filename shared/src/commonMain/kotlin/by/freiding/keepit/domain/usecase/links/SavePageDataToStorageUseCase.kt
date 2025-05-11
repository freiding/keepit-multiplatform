package by.freiding.keepit.domain.usecase.links

import by.freiding.keepit.data.local.database.dao.StoredLinksDao
import by.freiding.keepit.data.local.database.entity.StoredLinkEntity
import by.freiding.keepit.domain.usecase.UseCase
import by.freiding.keepit.domain.usecase.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class SavePageDataToStorageUseCaseParams(
    val url: String,
    val title: String?,
    val description: String?,
    val imageUrl: String?
)

class SavePageDataToStorageUseCase(
    private val pageDataDao: StoredLinksDao
): UseCase<SavePageDataToStorageUseCaseParams, UseCaseResult<String>>() {

    @OptIn(ExperimentalUuidApi::class)
    override fun invoke(params: SavePageDataToStorageUseCaseParams): Flow<UseCaseResult<String>> = flow {
        emit(UseCaseResult.loading())
        val id = Uuid.random().toHexString()
        val entity = StoredLinkEntity(
            id = id,
            title = params.title,
            description = params.description,
            imageUrl = params.imageUrl,
            url = params.url,
            isRead = false
        )
        pageDataDao.insert(entity)
        emit(UseCaseResult.success(id))

    }.catch { error ->
        emit(UseCaseResult.failure(error))
    }
}