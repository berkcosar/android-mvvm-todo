package info.tuver.todo.ui.todo.todoTagEditDialog

import info.tuver.todo.domain.TagDomain
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseDialogFragmentViewModel

class TodoTagEditDialogFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val tagDomain: TagDomain) : BaseDialogFragmentViewModel(coroutineDispatcherProvider) {

    init {
        subscribe(tagDomain.tagUpdatedSubject) { completedEvent.call() }
        subscribe(tagDomain.tagDeletedSubject) { completedEvent.call() }
    }

}