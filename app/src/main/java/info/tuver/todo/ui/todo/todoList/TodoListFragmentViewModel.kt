package info.tuver.todo.ui.todo.todoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.tuver.todo.domain.TagDomain
import info.tuver.todo.domain.TodoDomain
import info.tuver.todo.extension.add
import info.tuver.todo.extension.contains
import info.tuver.todo.extension.remove
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.model.TagModel
import info.tuver.todo.model.TodoModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoListFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val todoDomain: TodoDomain, private val tagDomain: TagDomain) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private var deletedTodoPosition: Int = 0

    private val mutableSelectedTagListValue = MutableLiveData<List<TagModel>>()

    private val mutableTodoListValue = MutableLiveData<List<TodoModel>>()

    val todoUpdatedEvent = SingleLiveEvent<TodoModel>()

    val todoDeletedEvent = SingleLiveEvent<Void>()

    val todoListValue: LiveData<List<TodoModel>>
        get() = mutableTodoListValue

    val selectedTagListValue: LiveData<List<TagModel>>
        get() = mutableSelectedTagListValue

    init {
        subscribe(todoDomain.todoCreatedSubject) { onTodoCreated(it) }
    }

    private fun onTodoCreated(todo: TodoModel) {
        mutableTodoListValue.add(todo)
    }

    fun onLoadTodoListRequest() {
        asyncOnIo(
            { todoDomain.getTodoList() },
            { mutableTodoListValue.value = it }
        )
    }

    fun onDeleteTodoRequest(position: Int) {
        todoListValue.value?.get(position)?.let { todo ->
            asyncOnIo(
                { todoDomain.deleteTodo(todo) },
                {
                    deletedTodoPosition = position
                    mutableTodoListValue.remove(todo)

                    todoDeletedEvent.call()
                }
            )
        }
    }

    fun onUpdateTodoCompletedValueRequest(todo: TodoModel, completed: Boolean) {
        asyncOnIo(
            { todoDomain.updateTodoCompleted(todo, completed) },
            { todoUpdatedEvent.postValue(todo) }
        )
    }

    fun onUndoDeleteTodoRequest() {
        asyncOnIo(
            { todoDomain.undoDeleteTodo() },
            {
                it?.let { todo ->
                    mutableTodoListValue.add(todo, deletedTodoPosition)
                }
            }
        )
    }

    fun onTodoTagClicked(tag: TagModel) {
        if (!mutableSelectedTagListValue.contains(tag)) {
            mutableSelectedTagListValue.add(tag).also { selectedTagList ->
                asyncOnIo(
                    { todoDomain.getTodoList(selectedTagList) },
                    { mutableTodoListValue.value = it }
                )
            }
        }
    }

    fun onRemoveTagFilterButtonClicked(tag: TagModel) {
        mutableSelectedTagListValue.remove(tag).also { selectedTagList ->
            asyncOnIo(
                { todoDomain.getTodoList(selectedTagList) },
                { mutableTodoListValue.value = it }
            )
        }
    }

}
