package info.tuver.todo.ui.todo.todoCreate

import androidx.databinding.ObservableField
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.extension.STRING_EMPTY
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoCreateViewModel(private val todoRepository: TodoRepository) : BaseFragmentViewModel() {

    val newTodoContentValue = ObservableField<String>()

    fun onCreateTodoRequest() {
        newTodoContentValue.get()?.let {
            if (it.isNotBlank()) {
                asyncOnIO(
                        { todoRepository.createTodo(it) },
                        { newTodoContentValue.set(STRING_EMPTY) }
                )
            }
        }
    }

}