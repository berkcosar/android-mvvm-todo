package info.tuver.todo.data.source.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.data.source.local.dao.TodoDao
import info.tuver.todo.extension.add
import info.tuver.todo.extension.remove
import info.tuver.todo.extension.replace
import java.util.*

class TodoLocalRepository(private val todoDao: TodoDao) : TodoRepository {

    private val mutableTodoList = MutableLiveData<List<TodoModel>>()

    override val todoList: LiveData<List<TodoModel>> = mutableTodoList

    override suspend fun refreshTodoList() {
        mutableTodoList.postValue(todoDao.selectList())
    }

    override suspend fun createTodo(content: String) {
        val todo = TodoModel(content, false, Date())
        val id = todoDao.insert(todo)

        mutableTodoList.add(todoDao.selectById(id))
    }

    override suspend fun deleteTodo(todo: TodoModel) {
        todoDao.delete(todo.id)
        mutableTodoList.remove(todo)
    }

    override suspend fun setAsCompleted(todo: TodoModel) {
        todoDao.updateCompleted(todo.id, true)
        mutableTodoList.replace(todo, todoDao.selectById(todo.id))
    }

    override suspend fun setAsNotCompleted(todo: TodoModel) {
        todoDao.updateCompleted(todo.id, false)
        mutableTodoList.replace(todo, todoDao.selectById(todo.id))
    }

}