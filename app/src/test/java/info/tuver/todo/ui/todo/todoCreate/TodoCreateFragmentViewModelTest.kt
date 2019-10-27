package info.tuver.todo.ui.todo.todoCreate

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.extension.STRING_EMPTY
import info.tuver.todo.extension.once
import info.tuver.todo.extension.twice
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseViewModelTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class TodoCreateFragmentViewModelTest : BaseViewModelTest() {

    private lateinit var todoRepository: TodoRepository

    private lateinit var newTodoContentValueOnPropertyChangedCallback: Observable.OnPropertyChangedCallback

    private lateinit var newTodoCreatedEventObserver: Observer<Any>

    private lateinit var viewModel: TodoCreateFragmentViewModel

    override fun onSetup(coroutineDispatcherProvider: CoroutineDispatcherProvider) {
        todoRepository = mock()
        newTodoContentValueOnPropertyChangedCallback = mock()
        newTodoCreatedEventObserver = mock()

        viewModel = TodoCreateFragmentViewModel(coroutineDispatcherProvider, todoRepository).apply {
            newTodoContentValue.addOnPropertyChangedCallback(newTodoContentValueOnPropertyChangedCallback)
            newTodoCreatedEvent.observeForever(newTodoCreatedEventObserver)
        }
    }

    @Test
    fun `should not create todo with empty content when on create todo request`() = runBlocking<Unit> {
        viewModel.newTodoContentValue.set("")
        viewModel.onCreateTodoRequest()

        verify(todoRepository, never()).createTodo(any())
        verify(newTodoContentValueOnPropertyChangedCallback, once()).onPropertyChanged(any(), any())
        verify(newTodoCreatedEventObserver, never()).onChanged(any())
    }

    @Test
    fun `should not create todo with blank content when on create todo request`() = runBlocking<Unit> {
        viewModel.newTodoContentValue.set("     ")
        viewModel.onCreateTodoRequest()

        verify(todoRepository, never()).createTodo(any())
        verify(newTodoContentValueOnPropertyChangedCallback, once()).onPropertyChanged(any(), any())
        verify(newTodoCreatedEventObserver, never()).onChanged(any())
    }

    @Test
    fun `should create todo when on create todo request`() = runBlocking<Unit> {
        val todoContent = "test todo content"
        val todo = TodoModel(todoContent, false, Date())
        val todoContentCaptor = argumentCaptor<String>()
        val newTodoContentObservableCaptor = argumentCaptor<ObservableField<String>>()

        whenever(todoRepository.createTodo(any())).doReturn(todo)

        viewModel.newTodoContentValue.set(todoContent)
        viewModel.onCreateTodoRequest()

        verify(todoRepository, once()).createTodo(todoContentCaptor.capture())
        verify(newTodoContentValueOnPropertyChangedCallback, twice()).onPropertyChanged(newTodoContentObservableCaptor.capture(), any())
        verify(newTodoCreatedEventObserver, once()).onChanged(any())
        assertEquals(todoContent, todoContentCaptor.lastValue)
        assertEquals(STRING_EMPTY, newTodoContentObservableCaptor.lastValue.get())
    }

}