package info.tuver.todo.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import info.tuver.todo.model.TagModel
import info.tuver.todo.model.TodoModel
import java.util.*

@Entity(tableName = "todo")
class TodoLocalModel(
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "completed") var completed: Boolean,
    @ColumnInfo(name = "createdDate") var createdDate: Date,
    @ColumnInfo(name = "deleted") var deleted: Boolean,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
) : BaseLocalModel<TodoModel>() {

    override fun toModel(): TodoModel {
        return toModel(emptyList())
    }

    fun toModel(tagList: List<TagModel>): TodoModel {
        return TodoModel(
            id,
            content,
            completed,
            createdDate,
            tagList
        )
    }

}