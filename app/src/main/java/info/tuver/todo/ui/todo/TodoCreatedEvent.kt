package info.tuver.todo.ui.todo

import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.ui.base.BaseEvent

class TodoCreatedEvent(val todo: TodoModel) : BaseEvent()