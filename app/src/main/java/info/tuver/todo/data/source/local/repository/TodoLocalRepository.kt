package info.tuver.todo.data.source.local.repository

import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.data.source.local.dao.TodoDao
import java.util.*

class TodoLocalRepository(private val todoDao: TodoDao) : TodoRepository {

    override suspend fun getTodo(id: Long): TodoModel {
        return todoDao.selectById(id)
    }

    override suspend fun getTodoList(): List<TodoModel> {
        return todoDao.selectList()
    }

    override suspend fun createTodo(content: String): TodoModel {
        val todo = TodoModel(content, false, Date())
        val id = todoDao.insert(todo)

        return getTodo(id)
    }

    override suspend fun deleteTodo(todo: TodoModel) {
        todoDao.delete(todo.id)
    }

    override suspend fun setAsCompleted(todo: TodoModel): TodoModel {
        todoDao.updateCompleted(todo.id, true)

        return getTodo(todo.id)
    }

    override suspend fun setAsNotCompleted(todo: TodoModel): TodoModel {
        todoDao.updateCompleted(todo.id, false)

        return getTodo(todo.id)
    }

}