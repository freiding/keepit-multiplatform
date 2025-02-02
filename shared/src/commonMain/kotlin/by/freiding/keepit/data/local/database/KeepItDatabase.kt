package by.freiding.keepit.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import by.freiding.keepit.data.local.database.converter.InstantTypeConverter
import by.freiding.keepit.data.local.database.dao.PageDataDao
import by.freiding.keepit.data.local.database.entity.PageDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO


@Database(
    entities = [
        PageDataEntity::class
    ],
    version = 1,
)
@TypeConverters(InstantTypeConverter::class)
@ConstructedBy(KeppItDatabaseConstructor::class)
abstract class KeepItDatabase: RoomDatabase() {
    abstract val pageDataDao: PageDataDao
}

expect object KeppItDatabaseConstructor: RoomDatabaseConstructor<KeepItDatabase> {
    override fun initialize(): KeepItDatabase
}

fun getKeepItDatabase(
    builder: RoomDatabase.Builder<KeepItDatabase>
): KeepItDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .addMigrations(

        )
        .fallbackToDestructiveMigration(true)
        .build()
}