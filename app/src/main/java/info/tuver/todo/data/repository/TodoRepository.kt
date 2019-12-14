package info.tuver.todo.data.repository

import info.tuver.todo.model.TodoModel

interface TodoRepository {

    suspend fun getTodoList(): List<TodoModel>

    suspend fun getTodoList(tagIdList: List<Long>): List<TodoModel>

    suspend fun createTodo(content: String, tagIdList: List<Long>): TodoModel

    suspend fun deleteTodo(todoId: Long)

    suspend fun undoDeleteTodo(todoId: Long)

    suspend fun updateTodoCompleted(todoId: Long, completed: Boolean)

}