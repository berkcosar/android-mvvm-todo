package info.tuver.todo.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "todo_tag",
    primaryKeys = arrayOf("todoId", "tagId"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = TodoLocalModel::class,
            parentColumns = ["id"],
            childColumns = ["todoId"]
        ),
        ForeignKey(
            entity = TagLocalModel::class,
            parentColumns = ["id"],
            childColumns = ["tagId"]
        )
    )
)
class TodoTagLocalModel(
    @ColumnInfo(name = "todoId") var todoId: Long,
    @ColumnInfo(name = "tagId") var tagId: Long
) : BaseLocalModel<Any>() {

    override fun toModel(): Any {
        throw UnsupportedOperationException("TodoTagLocalModel cannot be converted into a model!")
    }

}