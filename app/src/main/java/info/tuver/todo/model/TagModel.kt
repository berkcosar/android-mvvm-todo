package info.tuver.todo.model

data class TagModel(var id: Long, var name: String, var color: String) : BaseModel() {

    override val idCode: Long
        get() = id

}