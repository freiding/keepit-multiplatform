package by.freiding.keepit.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import by.freiding.keepit.data.local.database.KeepItDatabase

fun getKeepItDatabaseBuilder(context: Context): RoomDatabase.Builder<KeepItDatabase> {
    val dbFile = context.getDatabasePath("keepit_database.db")
    return Room.databaseBuilder<KeepItDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
}