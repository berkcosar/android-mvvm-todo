package info.tuver.todo.data.repository

import info.tuver.todo.data.model.TodoModel

interface TodoRepository {

    suspend fun getTodoList(): List<TodoModel>

    suspend fun createTodo(content: String, tagIdList: List<Long>): TodoModel

    suspend fun deleteTodo(todoId: Long)

    suspend fun undoDeleteTodo(todoId: Long)

    suspend fun updateTodoCompleted(todoId: Long, completed: Boolean)

    suspend fun addTagToTodo(todoId: Long, tagId: Long)

    suspend fun removeTagFromTodo(todoId: Long, tagId: Long)

}