package info.tuver.todo.ui.todo.todoCreate

import androidx.databinding.ObservableField
import info.tuver.todo.domain.TodoDomain
import info.tuver.todo.extension.STRING_EMPTY
import info.tuver.todo.model.TagModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoCreateFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val todoDomain: TodoDomain) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private var selectedTagIdList = emptyList<Long>()

    val newTodoContentValue = ObservableField<String>()

    fun onCreateTodoRequest() {
        newTodoContentValue.get()?.let {
            if (it.isNotBlank()) {
                asyncOnIo(
                    { todoDomain.createTodo(it, selectedTagIdList) },
                    { newTodoContentValue.set(STRING_EMPTY) }
                )
            }
        }
    }

    fun onTodoTagSelectionChanged(tagList: List<TagModel>) {
        selectedTagIdList = tagList.map { it.id }
    }

}