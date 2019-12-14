package info.tuver.todo.ui.todo.todoList

import info.tuver.todo.model.TagModel
import info.tuver.todo.ui.base.BaseAdapterActions

interface TodoTagFilterAdapterActions : BaseAdapterActions {

    fun onRemoveTagFilterButtonClicked(tag: TagModel)

}