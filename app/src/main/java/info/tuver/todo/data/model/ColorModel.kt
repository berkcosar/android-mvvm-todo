package info.tuver.todo.data.model

class ColorModel(var color: String) : BaseModel() {

    override val idCode: Long
        get() = color.hashCode().toLong()

}