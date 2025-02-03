package by.freiding.keepit.domain.usecase.links

import by.freiding.keepit.data.local.database.dao.StoredLinksDao
import by.freiding.keepit.domain.converter.StoredLinksConverter
import by.freiding.keepit.domain.model.StoredLinkData
import by.freiding.keepit.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

data class FetchStoredLinksListUseCaseParams(
    val isRead: Boolean? = null
)

class FetchStoredLinksListUseCase(
    private val pageDataDao: StoredLinksDao,
    private val pageDataConverter: StoredLinksConverter,
): UseCase<FetchStoredLinksListUseCaseParams, List<StoredLinkData>>() {

    override fun invoke(params: FetchStoredLinksListUseCaseParams): Flow<List<StoredLinkData>> = flow {
        val dataFlow = if (params.isRead != null) {
            pageDataDao.fetchPageDataList(params.isRead)
        } else {
            pageDataDao.fetchPageDataList()
        }

        dataFlow.collect { entities ->
            emit(entities.map(pageDataConverter::toStoredPageData))
        }
    }.flowOn(Dispatchers.IO)
}