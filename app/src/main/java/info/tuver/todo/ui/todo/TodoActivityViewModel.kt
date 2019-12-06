package info.tuver.todo.ui.todo

import androidx.databinding.ObservableBoolean
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseActivityViewModel

class TodoActivityViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) : BaseActivityViewModel(coroutineDispatcherProvider) {

    val addTodoButtonVisibleValue = ObservableBoolean(true)

    val showTodoCreateViewEvent = SingleLiveEvent<Void>()

    fun onAddTodoButtonClicked() {
        addTodoButtonVisibleValue.set(false)
        showTodoCreateViewEvent.call()
    }

    fun onBackButtonClicked() {
        addTodoButtonVisibleValue.set(true)
    }

}