package by.freiding.keepit.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import by.freiding.keepit.data.local.database.entity.StoredLinkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoredLinksDao: BaseDao<StoredLinkEntity> {

    @Query("SELECT * FROM links ORDER BY createdAt DESC")
    fun fetchPageDataList(): Flow<List<StoredLinkEntity>>

    @Query("SELECT * FROM links WHERE isRead=:isRead ORDER BY createdAt DESC")
    fun fetchPageDataList(isRead: Boolean): Flow<List<StoredLinkEntity>>

    @Query("SELECT * FROM links WHERE id=:id LIMIT 1")
    fun fetchPageDataById(id: String): Flow<StoredLinkEntity?>
}