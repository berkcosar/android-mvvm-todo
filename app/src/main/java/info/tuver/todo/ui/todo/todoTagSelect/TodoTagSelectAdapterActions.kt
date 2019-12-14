package info.tuver.todo.ui.todo.todoTagSelect

import info.tuver.todo.model.TagSelectModel
import info.tuver.todo.ui.base.BaseAdapterActions

interface TodoTagSelectAdapterActions : BaseAdapterActions {

    fun onTodoTagSelectClicked(tagSelect: TagSelectModel)

    fun  onTodoTagSelectLongClicked(tagSelect: TagSelectModel)

    fun onCreateNewTagButtonClicked()

}