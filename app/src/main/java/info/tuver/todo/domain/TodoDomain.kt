package info.tuver.todo.domain

import info.tuver.todo.model.TagModel
import info.tuver.todo.model.TodoModel
import io.reactivex.subjects.Subject

interface TodoDomain {

    val todoCreatedSubject: Subject<TodoModel>

    val todoUpdatedSubject: Subject<TodoModel>

    val todoDeletedSubject: Subject<TodoModel>

    val todoUnDeletedSubject: Subject<TodoModel>

    suspend fun getTodoList(): List<TodoModel>

    suspend fun getTodoList(tagList: List<TagModel>): List<TodoModel>

    suspend fun createTodo(content: String, tagIdList: List<Long>): TodoModel

    suspend fun updateTodoCompleted(todo: TodoModel, completed: Boolean)

    suspend fun deleteTodo(todo: TodoModel)

    suspend fun undoDeleteTodo(): TodoModel?

}