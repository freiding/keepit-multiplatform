package by.freiding.keepit.data.local.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import by.freiding.keepit.data.local.database.entity.BaseEntity

interface BaseDao<T: BaseEntity> {

    @Insert
    suspend fun insert(entity: T)

    @Insert
    suspend fun insertAll(entities: List<T>)

    @Update
    suspend fun update(entity: T)

    @Delete
    suspend fun hardDelete(entity: T)

}