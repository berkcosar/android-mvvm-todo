package info.tuver.todo.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.source.local.dao.TodoDao
import info.tuver.todo.data.source.local.database.converter.DateToLongConverter

@Database(
    version = 1,
    entities = [TodoModel::class]
)
@TypeConverters(DateToLongConverter::class)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todo(): TodoDao

}