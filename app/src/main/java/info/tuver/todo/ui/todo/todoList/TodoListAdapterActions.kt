package info.tuver.todo.ui.todo.todoList

import info.tuver.todo.model.TodoModel
import info.tuver.todo.ui.base.BaseAdapterActions

interface TodoListAdapterActions : BaseAdapterActions, TodoTagListAdapterActions {

    fun onTodoCompletedCheckboxValueChanged(todo: TodoModel, checked: Boolean)

}