package info.tuver.todo.data.repository

import androidx.lifecycle.LiveData
import info.tuver.todo.data.model.TodoModel

interface TodoRepository {

    val todoList: LiveData<List<TodoModel>>

    suspend fun refreshTodoList()

    suspend fun createTodo(content: String)

    suspend fun deleteTodo(todo: TodoModel)

    suspend fun setAsCompleted(todo: TodoModel)

    suspend fun setAsNotCompleted(todo: TodoModel)

}