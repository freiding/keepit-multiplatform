package by.freiding.keepit.data.local.database.converter

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class InstantTypeConverter {

    @TypeConverter
    fun fromInstant(date: Instant?): Long? {
        return date?.toEpochMilliseconds()
    }

    @TypeConverter
    fun toInstant(time: Long?): Instant? {
        return time?.let { Instant.fromEpochMilliseconds(time) }
    }

}