package info.tuver.todo.ui.tag.tagEdit

import info.tuver.todo.data.model.TagModel
import info.tuver.todo.ui.base.BaseEvent

class TagEditEvents {

    class TagEditedEvent(val tag: TagModel) : BaseEvent()

    class TagDeletedEvent(val tag: TagModel) : BaseEvent()

}