package info.tuver.todo.ui.todo.todoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.extension.add
import info.tuver.todo.extension.remove
import info.tuver.todo.extension.replace
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoListFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val todoRepository: TodoRepository) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private val mutableTodoList = MutableLiveData<List<TodoModel>>()

    val todoList: LiveData<List<TodoModel>>
        get() = mutableTodoList

    fun onLoadTodoListRequest() {
        asyncOnIo(
            { todoRepository.getTodoList() },
            { result -> mutableTodoList.value = result }
        )
    }

    fun onDeleteTodoRequest(position: Int) {
        todoList.value?.get(position)?.let { todo ->
            asyncOnIo(
                { todoRepository.deleteTodo(todo) },
                { mutableTodoList.remove(todo) }
            )
        }
    }

    fun onUpdateTodoCompletedValueRequest(todo: TodoModel, completed: Boolean) {
        asyncOnIo(
            {
                when {
                    completed -> todoRepository.setAsCompleted(todo)
                    else -> todoRepository.setAsNotCompleted(todo)
                }
            },
            { result ->
                mutableTodoList.replace(todo, result)
            }
        )
    }

    fun onTodoCreatedEvent(todo: TodoModel) {
        mutableTodoList.add(todo)
    }

}
