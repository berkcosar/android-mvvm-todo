package info.tuver.todo.ui.todo.todoList

import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.ui.base.BaseAdapterActions

interface TodoListAdapterActions : BaseAdapterActions {

    fun onTodoCompletedCheckboxValueChanged(todo: TodoModel, checked: Boolean)

}