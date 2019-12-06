package info.tuver.todo.data.model

import java.util.*

data class TodoModel(val id: Long, var content: String, var completed: Boolean, var createdDate: Date, var tagList: List<TagModel>) : BaseModel() {

    override val idCode: Long
        get() = id

}