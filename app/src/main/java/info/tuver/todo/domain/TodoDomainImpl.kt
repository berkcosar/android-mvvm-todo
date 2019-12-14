package info.tuver.todo.domain

import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.model.TagModel
import info.tuver.todo.model.TodoModel
import io.reactivex.subjects.PublishSubject

class TodoDomainImpl(private val todoRepository: TodoRepository) : TodoDomain {

    private var lastDeletedTodo: TodoModel? = null

    override val todoCreatedSubject = PublishSubject.create<TodoModel>()

    override val todoUpdatedSubject = PublishSubject.create<TodoModel>()

    override val todoDeletedSubject = PublishSubject.create<TodoModel>()

    override val todoUnDeletedSubject = PublishSubject.create<TodoModel>()

    override suspend fun getTodoList(): List<TodoModel> {
        return todoRepository.getTodoList()
    }

    override suspend fun getTodoList(tagList: List<TagModel>): List<TodoModel> {
        return when {
            tagList.isEmpty() -> getTodoList()
            else -> todoRepository.getTodoList(tagList.map { it.id })

        }
    }

    override suspend fun createTodo(content: String, tagIdList: List<Long>): TodoModel {
        return todoRepository.createTodo(content, tagIdList).also {
            todoCreatedSubject.onNext(it)
        }
    }

    override suspend fun updateTodoCompleted(todo: TodoModel, completed: Boolean) {
        todoRepository.updateTodoCompleted(todo.id, completed)

        todo.also {
            it.completed = completed
            todoUpdatedSubject.onNext(todo)
        }
    }

    override suspend fun deleteTodo(todo: TodoModel) {
        todoRepository.deleteTodo(todo.id)
        lastDeletedTodo = todo
        todoDeletedSubject.onNext(todo)
    }

    override suspend fun undoDeleteTodo(): TodoModel? {
        lastDeletedTodo?.let {
            todoRepository.undoDeleteTodo(it.id)
            todoUnDeletedSubject.onNext(it)
        }

        return lastDeletedTodo
    }

}