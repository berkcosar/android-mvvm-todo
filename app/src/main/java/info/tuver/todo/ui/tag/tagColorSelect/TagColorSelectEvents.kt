package info.tuver.todo.ui.tag.tagColorSelect

import info.tuver.todo.data.model.ColorModel
import info.tuver.todo.ui.base.BaseEvent

class TagColorSelectEvents {

    class TagColorSelectedEvent(val color: ColorModel) : BaseEvent()

}