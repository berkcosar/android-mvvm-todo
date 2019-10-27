package info.tuver.todo.data.source.local.repository

import com.nhaarman.mockitokotlin2.*
import info.tuver.todo.base.BaseTest
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.source.local.dao.TodoDao
import info.tuver.todo.extension.once
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class TodoLocalRepositoryTest : BaseTest() {

    private val todoId = 0L

    private val todoContent = "test todo content"

    private val todo = TodoModel(todoContent, false, Date()).apply { id = todoId }

    private val todoList = listOf(todo)

    private lateinit var todoDao: TodoDao

    private lateinit var todoLocalRepository: TodoLocalRepository

    override fun onSetup() {
        todoDao = mock()
        todoLocalRepository = TodoLocalRepository(todoDao)
    }

    @Test
    fun `should call selectById when get todo`() = runBlocking {
        whenever(todoDao.selectById(todoId)).doReturn(todo)

        val getTodoResult = todoLocalRepository.getTodo(todoId)

        verify(todoDao, once()).selectById(todoId)
        assertEquals(todo, getTodoResult)
    }

    @Test
    fun `should call select list when get todo list`() = runBlocking {
        whenever(todoDao.selectList()).doReturn(todoList)

        val getTodoListResult = todoLocalRepository.getTodoList()

        verify(todoDao, once()).selectList()
        assertEquals(todoList, getTodoListResult)
    }

    @Test
    fun `should call insert and select by id when create todo`() = runBlocking {
        val todoCaptor = argumentCaptor<TodoModel>()

        whenever(todoDao.insert(any())).doReturn(todoId)
        whenever(todoDao.selectById(todoId)).doReturn(todo)

        val createTodoResult = todoLocalRepository.createTodo(todoContent)

        verify(todoDao, once()).insert(todoCaptor.capture())
        verify(todoDao, once()).selectById(todoId)
        assertEquals(todoCaptor.lastValue.content, todoContent)
        assertEquals(todoCaptor.lastValue.completed, false)
        assertEquals(createTodoResult, todo)
    }

    @Test
    fun `should call delete when delete todo`() = runBlocking {
        todoLocalRepository.deleteTodo(todo)

        verify(todoDao, once()).delete(todoId)
    }

    @Test
    fun `should call set as completed and select by id when set as completed`() = runBlocking<Unit> {
        todoLocalRepository.setAsCompleted(todo)

        verify(todoDao, once()).updateCompleted(todoId, true)
        verify(todoDao, once()).selectById(todoId)
    }

    @Test
    fun `should call set as completed and select by id when set as not completed`() = runBlocking<Unit> {
        todoLocalRepository.setAsNotCompleted(todo)

        verify(todoDao, once()).updateCompleted(todoId, false)
        verify(todoDao, once()).selectById(todoId)
    }


}