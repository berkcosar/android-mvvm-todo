package info.tuver.todo.ui.todo.todoCreate

import androidx.databinding.ObservableField
import info.tuver.todo.data.model.TagModel
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.repository.TagRepository
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.extension.STRING_EMPTY
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoCreateFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val todoRepository: TodoRepository, private val tagRepository: TagRepository) :
    BaseFragmentViewModel(coroutineDispatcherProvider) {

    private var selectedTagIdList = emptyList<Long>()

    val newTodoContentValue = ObservableField<String>()

    val newTodoCreatedEvent = SingleLiveEvent<TodoModel>()

    fun onCreateTodoRequest() {
        newTodoContentValue.get()?.let {
            if (it.isNotBlank()) {
                asyncOnIo(
                    { todoRepository.createTodo(it, selectedTagIdList) },
                    {
                        newTodoContentValue.set(STRING_EMPTY)
                        newTodoCreatedEvent.value = it
                    }
                )
            }
        }
    }

    fun onTodoTagSelectionChangedEvent(tagList: List<TagModel>) {
        selectedTagIdList = tagList.map { it.id }
    }

}