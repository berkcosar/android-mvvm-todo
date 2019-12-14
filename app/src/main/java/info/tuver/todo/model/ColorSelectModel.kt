package info.tuver.todo.model

data class ColorSelectModel(var color: ColorModel, var selected: Boolean = false) : BaseModel() {

    override val idCode: Long
        get() = color.idCode

}