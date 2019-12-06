package info.tuver.todo.ui.base

import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.provider.CoroutineDispatcherProvider

abstract class BaseDialogFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) : BaseViewModel(coroutineDispatcherProvider) {

    val dialogCompletedEvent = SingleLiveEvent<Void>()

}