package info.tuver.todo.ui.todo.todoTagEditDialog

import info.tuver.todo.data.model.TagModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseDialogFragmentViewModel

class TodoTagEditDialogFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) : BaseDialogFragmentViewModel(coroutineDispatcherProvider) {

    fun onTagEdited(tag: TagModel) {
        dialogCompletedEvent.call()
    }

    fun onTagDeleted(tag: TagModel) {
        dialogCompletedEvent.call()
    }

}