package info.tuver.todo.data.model

data class ColorSelectModel(var color: ColorModel, var selected: Boolean = false) : BaseModel() {

    override val idCode: Long
        get() = color.idCode

}