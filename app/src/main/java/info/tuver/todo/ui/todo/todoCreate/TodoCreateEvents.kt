package info.tuver.todo.ui.todo.todoCreate

import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.ui.base.BaseEvent

class TodoCreateEvents {

    class TodoCreatedEvent(val todo: TodoModel) : BaseEvent()

}