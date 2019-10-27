package info.tuver.todo.ui.todo.todoList

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.repository.TodoRepository
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
class TodoListFragmentViewModelTest : BaseViewModelTest() {

    private val date = Date()

    private val todoAtPosition0 = TodoModel("test todo content 1", false, date).apply { id = 0 }

    private val todoAtPosition1 = TodoModel("test todo content 2", true, date).apply { id = 1 }

    private val todoList = listOf(todoAtPosition0, todoAtPosition1)

    private lateinit var todoRepository: TodoRepository

    private lateinit var todoListObserver: Observer<List<TodoModel>>

    private lateinit var viewModel: TodoListFragmentViewModel

    override fun onSetup(coroutineDispatcherProvider: CoroutineDispatcherProvider) {
        todoRepository = mock()
        todoListObserver = mock()

        viewModel = TodoListFragmentViewModel(coroutineDispatcherProvider, todoRepository).apply {
            todoList.observeForever(todoListObserver)
        }
    }

    @Test
    fun `should load todo list when on load todo list request`() = runBlocking<Unit> {
        val todoListCaptor = argumentCaptor<List<TodoModel>>()

        whenever(todoRepository.getTodoList()).doReturn(todoList)

        viewModel.onLoadTodoListRequest()

        verify(todoRepository, once()).getTodoList()
        verify(todoListObserver, once()).onChanged(todoListCaptor.capture())
        assertEquals(todoListCaptor.lastValue.size, 2)
        assertEquals(todoListCaptor.lastValue.get(0), todoAtPosition0)
        assertEquals(todoListCaptor.lastValue.get(1), todoAtPosition1)
    }

    @Test
    fun `should delete todo when on delete todo request`() = runBlocking<Unit> {
        val todoListCaptor = argumentCaptor<List<TodoModel>>()

        whenever(todoRepository.getTodoList()).doReturn(todoList)

        viewModel.onLoadTodoListRequest()
        viewModel.onDeleteTodoRequest(0)

        verify(todoRepository, once()).deleteTodo(todoAtPosition0)
        verify(todoListObserver, twice()).onChanged(todoListCaptor.capture())
        assertEquals(todoListCaptor.lastValue.size, 1)
        assertEquals(todoListCaptor.lastValue.get(0), todoAtPosition1)
    }

    @Test
    fun `should update todo as completed when on update todo completed value request with true`() = runBlocking<Unit> {
        val completedTodo = todoAtPosition0.copy(completed = true)
        val todoListCaptor = argumentCaptor<List<TodoModel>>()

        whenever(todoRepository.getTodoList()).doReturn(todoList)
        whenever(todoRepository.setAsCompleted(todoAtPosition0)).doReturn(completedTodo)

        viewModel.onLoadTodoListRequest()
        viewModel.onUpdateTodoCompletedValueRequest(todoAtPosition0, true)

        verify(todoRepository, once()).setAsCompleted(todoAtPosition0)
        verify(todoListObserver, twice()).onChanged(todoListCaptor.capture())
        assertEquals(todoListCaptor.lastValue.get(0), completedTodo)
    }

    @Test
    fun `should update todo as not completed when on update todo completed value request with false`() = runBlocking<Unit> {
        val notCompletedTodo = todoAtPosition1.copy(completed = false)
        val todoListCaptor = argumentCaptor<List<TodoModel>>()

        whenever(todoRepository.getTodoList()).doReturn(todoList)
        whenever(todoRepository.setAsNotCompleted(todoAtPosition1)).doReturn(notCompletedTodo)

        viewModel.onLoadTodoListRequest()
        viewModel.onUpdateTodoCompletedValueRequest(todoAtPosition1, false)

        verify(todoRepository, once()).setAsNotCompleted(todoAtPosition1)
        verify(todoListObserver, twice()).onChanged(todoListCaptor.capture())
        assertEquals(todoListCaptor.lastValue.get(1), notCompletedTodo)
    }

}