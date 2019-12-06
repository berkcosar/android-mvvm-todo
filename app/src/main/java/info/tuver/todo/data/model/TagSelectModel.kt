package info.tuver.todo.data.model

data class TagSelectModel(var tag: TagModel, var selected: Boolean = false) : BaseModel() {

    override val idCode: Long
        get() = tag.idCode

}