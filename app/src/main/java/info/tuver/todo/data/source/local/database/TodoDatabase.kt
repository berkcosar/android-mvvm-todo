package info.tuver.todo.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import info.tuver.todo.data.source.local.dao.TagDao
import info.tuver.todo.data.source.local.dao.TodoDao
import info.tuver.todo.data.source.local.dao.TodoTagDao
import info.tuver.todo.data.source.local.database.converter.DateToLongConverter
import info.tuver.todo.data.source.local.model.TagLocalModel
import info.tuver.todo.data.source.local.model.TodoLocalModel
import info.tuver.todo.data.source.local.model.TodoTagLocalModel

@Database(
    version = 1,
    entities = arrayOf(
        TodoLocalModel::class,
        TagLocalModel::class,
        TodoTagLocalModel::class
    )
)
@TypeConverters(DateToLongConverter::class)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todo(): TodoDao

    abstract fun tag(): TagDao

    abstract fun todoTag(): TodoTagDao

}