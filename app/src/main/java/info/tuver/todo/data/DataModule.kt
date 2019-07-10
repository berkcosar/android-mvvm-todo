package info.tuver.todo.data

import androidx.room.Room
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.data.source.local.database.TodoDatabase
import info.tuver.todo.data.source.local.repository.TodoLocalRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TodoDatabase::class.java,
            "todo-db"
        ).build()
    }
    single { get<TodoDatabase>().todo() }
    single { TodoLocalRepository(get()) as TodoRepository }
}