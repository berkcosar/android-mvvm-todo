package info.tuver.todo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todo")
data class TodoModel(var content: String,
                     var completed: Boolean,
                     var createdDate: Date) : BaseModel() {

    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0

}