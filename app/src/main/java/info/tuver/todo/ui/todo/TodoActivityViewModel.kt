package info.tuver.todo.ui.todo

import androidx.databinding.ObservableField
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseActivityViewModel

class TodoActivityViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) : BaseActivityViewModel(coroutineDispatcherProvider) {

    val addTodoButtonVisibleValue = ObservableField<Boolean>(true)

    val createTodoViewVisibleValue = ObservableField<Boolean>(false)

    fun onAddTodoButtonClicked() {
        addTodoButtonVisibleValue.set(false)
        createTodoViewVisibleValue.set(true)
    }

    fun onBackButtonClicked() {
        if (createTodoViewVisibleValue.get() == false) {
            completedEvent.call()
        } else {
            addTodoButtonVisibleValue.set(true)
            createTodoViewVisibleValue.set(false)
        }
    }

}