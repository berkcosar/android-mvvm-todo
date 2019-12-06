package info.tuver.todo.ui.todo.todoTagSelect

import info.tuver.todo.data.model.TagModel
import info.tuver.todo.ui.base.BaseEvent

class TodoTagSelectEvents {

    class TodoTagSelectionChangedEvent(val tagList: List<TagModel>) : BaseEvent()

}