package info.tuver.todo.ui.todo.todoList

import androidx.lifecycle.Transformations
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoListFragmentViewModel(private val todoRepository: TodoRepository) : BaseFragmentViewModel() {

    val todoList = Transformations.map(todoRepository.todoList, { it })

    fun onRefreshTodoListRequest() {
        asyncOnIO {
            todoRepository.refreshTodoList()
        }
    }

    fun onDeleteTodoRequest(position: Int) {
        todoList.value?.get(position)?.let {
            asyncOnIO {
                todoRepository.deleteTodo(it)
            }
        }
    }

    fun onUpdateTodoCompletedValueRequest(todo: TodoModel, completed: Boolean) {
        asyncOnIO {
            when {
                completed -> todoRepository.setAsCompleted(todo)
                else -> todoRepository.setAsNotCompleted(todo)
            }
        }
    }

}
