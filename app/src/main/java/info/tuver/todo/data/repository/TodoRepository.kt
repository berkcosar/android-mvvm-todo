package info.tuver.todo.data.repository

import info.tuver.todo.data.model.TodoModel

interface TodoRepository {

    suspend fun getTodo(id: Long): TodoModel

    suspend fun getTodoList(): List<TodoModel>

    suspend fun createTodo(content: String): TodoModel

    suspend fun deleteTodo(todo: TodoModel)

    suspend fun setAsCompleted(todo: TodoModel): TodoModel

    suspend fun setAsNotCompleted(todo: TodoModel): TodoModel

}