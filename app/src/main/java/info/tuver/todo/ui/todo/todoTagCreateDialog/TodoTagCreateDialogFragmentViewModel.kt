package info.tuver.todo.ui.todo.todoTagCreateDialog

import info.tuver.todo.data.model.TagModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseDialogFragmentViewModel

class TodoTagCreateDialogFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) : BaseDialogFragmentViewModel(coroutineDispatcherProvider) {

    fun onTagCreated(tag: TagModel) {
        dialogCompletedEvent.call()
    }

}