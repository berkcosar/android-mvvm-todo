package info.tuver.todo.model

data class TagSelectModel(var tag: TagModel, var selected: Boolean = false) : BaseModel() {

    override val idCode: Long
        get() = tag.idCode

}