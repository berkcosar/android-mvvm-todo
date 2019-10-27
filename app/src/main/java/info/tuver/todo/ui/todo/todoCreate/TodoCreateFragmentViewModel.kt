package info.tuver.todo.ui.todo.todoCreate

import androidx.databinding.ObservableField
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.extension.STRING_EMPTY
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoCreateFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val todoRepository: TodoRepository) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    val newTodoContentValue = ObservableField<String>()

    val newTodoCreatedEvent = SingleLiveEvent<TodoModel>()

    fun onCreateTodoRequest() {
        newTodoContentValue.get()?.let {
            if (it.isNotBlank()) {
                asyncOnIo(
                    { todoRepository.createTodo(it) },
                    { result ->
                        newTodoContentValue.set(STRING_EMPTY)
                        newTodoCreatedEvent.value = result
                    }
                )
            }
        }
    }

}