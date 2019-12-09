package info.tuver.todo.ui.todo.todoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.extension.add
import info.tuver.todo.extension.remove
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoListFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val todoRepository: TodoRepository) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private var deletedTodo: TodoModel? = null

    private var deletedTodoPosition: Int = 0

    private val mutableTodoListValue = MutableLiveData<List<TodoModel>>()

    val todoUpdatedEvent = SingleLiveEvent<TodoModel>()

    val todoDeletedEvent = SingleLiveEvent<Void>()

    val todoListValue: LiveData<List<TodoModel>>
        get() = mutableTodoListValue

    fun onLoadTodoListRequest() {
        asyncOnIo(
            { todoRepository.getTodoList() },
            { mutableTodoListValue.value = it }
        )
    }

    fun onDeleteTodoRequest(position: Int) {
        todoListValue.value?.get(position)?.let { todo ->
            asyncOnIo(
                { todoRepository.deleteTodo(todo.id) },
                {
                    deletedTodo = todo
                    deletedTodoPosition = position

                    mutableTodoListValue.remove(todo)
                    todoDeletedEvent.call()
                }
            )
        }
    }

    fun onUpdateTodoCompletedValueRequest(todo: TodoModel, completed: Boolean) {
        asyncOnIo(
            { todoRepository.updateTodoCompleted(todo.id, completed) },
            {
                todo.completed = completed
                todoUpdatedEvent.postValue(todo)
            }
        )
    }

    fun onUndoDeleteTodoRequest() {
        deletedTodo?.let { todo ->
            asyncOnIo(
                { todoRepository.undoDeleteTodo(todo.id) },
                { mutableTodoListValue.add(todo, deletedTodoPosition) }
            )
        }
    }

    fun onTodoCreated(todo: TodoModel) {
        mutableTodoListValue.add(todo)
    }

}
