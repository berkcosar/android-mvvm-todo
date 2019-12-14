package info.tuver.todo.ui.todo.todoList

import info.tuver.todo.model.TagModel
import info.tuver.todo.ui.base.BaseAdapterActions

interface TodoTagListAdapterActions : BaseAdapterActions {

    fun onTodoTagClicked(tag: TagModel)

}