package info.tuver.todo.data.source.local.database.converter

import androidx.room.TypeConverter
import java.util.*

class DateToLongConverter {

    @TypeConverter
    fun fromLong(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toLong(date: Date?): Long? {
        return date?.time
    }

}