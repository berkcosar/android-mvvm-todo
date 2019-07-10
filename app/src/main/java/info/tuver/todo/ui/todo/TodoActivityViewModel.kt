package info.tuver.todo.ui.todo

import androidx.databinding.ObservableBoolean
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.ui.base.BaseActivityViewModel

class TodoActivityViewModel : BaseActivityViewModel() {

    val addTodoButtonVisibleValue = ObservableBoolean(true)

    val showTodoCreateViewEvent = SingleLiveEvent<Void>()

    fun onAddTodoFabClicked() {
        addTodoButtonVisibleValue.set(false)
        showTodoCreateViewEvent.call()
    }

    fun onBackButtonClicked() {
        addTodoButtonVisibleValue.set(true)
    }

}