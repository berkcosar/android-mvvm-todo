package info.tuver.todo.ui.tag.tagCreate

import info.tuver.todo.data.model.TagModel
import info.tuver.todo.ui.base.BaseEvent

class TagCreateEvents {

    class TagCreatedEvent(val tag: TagModel) : BaseEvent()

}