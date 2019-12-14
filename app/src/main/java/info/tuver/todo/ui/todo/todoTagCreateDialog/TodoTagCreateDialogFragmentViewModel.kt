package info.tuver.todo.ui.todo.todoTagCreateDialog

import info.tuver.todo.domain.TagDomain
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseDialogFragmentViewModel

class TodoTagCreateDialogFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, tagDomain: TagDomain) : BaseDialogFragmentViewModel(coroutineDispatcherProvider) {

    init {
        subscribe(tagDomain.tagCreatedSubject) { completedEvent.call() }
    }

}