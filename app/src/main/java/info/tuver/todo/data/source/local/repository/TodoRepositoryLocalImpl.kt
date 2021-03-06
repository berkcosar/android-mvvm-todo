package info.tuver.todo.data.source.local.repository

import info.tuver.todo.model.TagModel
import info.tuver.todo.model.TodoModel
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.data.source.local.dao.TodoDao
import info.tuver.todo.data.source.local.dao.TodoTagDao
import info.tuver.todo.data.source.local.model.TodoLocalModel
import info.tuver.todo.data.source.local.model.TodoTagLocalModel
import java.util.*

class TodoRepositoryLocalImpl(private val todoDao: TodoDao, private val todoTagDao: TodoTagDao) : TodoRepository {

    private suspend fun getTagList(todoId: Long): List<TagModel> {
        return todoDao.selectTagList(todoId).map { it.toModel() }
    }

    private suspend fun getTodo(todoId: Long): TodoModel {
        return todoDao.selectById(todoId).toModel(getTagList(todoId))
    }

    override suspend fun getTodoList(): List<TodoModel> {
        return todoDao.selectList().map { it.toModel(getTagList(it.id)) }
    }

    override suspend fun getTodoList(tagIdList: List<Long>): List<TodoModel> {
        return todoDao.selectList(tagIdList, tagIdList.count()).map { it.toModel(getTagList(it.id)) }
    }

    override suspend fun createTodo(content: String, tagIdList: List<Long>): TodoModel {
        val todo = TodoLocalModel(content.trim(), false, Date(), false)
        val id = todoDao.insert(todo)

        todoTagDao.insertList(tagIdList.map { TodoTagLocalModel(id, it) })

        return getTodo(id)
    }

    override suspend fun updateTodoCompleted(todoId: Long, completed: Boolean) {
        todoDao.updateCompleted(todoId, completed)
    }

    override suspend fun deleteTodo(todoId: Long) {
        todoDao.delete(todoId)
    }

    override suspend fun undoDeleteTodo(todoId: Long) {
        todoDao.undoDelete(todoId)
    }

}