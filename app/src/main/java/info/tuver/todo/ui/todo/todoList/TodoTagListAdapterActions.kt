package info.tuver.todo.ui.todo.todoList

import info.tuver.todo.data.model.TagModel
import info.tuver.todo.ui.base.BaseAdapterActions

interface TodoTagListAdapterActions : BaseAdapterActions {

    fun onTodoTagClicked(tag: TagModel)

}