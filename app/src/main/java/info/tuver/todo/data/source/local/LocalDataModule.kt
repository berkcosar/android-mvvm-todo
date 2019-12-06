package info.tuver.todo.data.source.local

import androidx.room.Room
import info.tuver.todo.data.repository.ColorRepository
import info.tuver.todo.data.repository.TagRepository
import info.tuver.todo.data.repository.TodoRepository
import info.tuver.todo.data.source.local.database.TodoDatabase
import info.tuver.todo.data.source.local.repository.ColorLocalRepository
import info.tuver.todo.data.source.local.repository.TagLocalRepository
import info.tuver.todo.data.source.local.repository.TodoLocalRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single {
        Room.databaseBuilder(androidContext(), TodoDatabase::class.java, "todo_db")
            .build()
    }
    single { get<TodoDatabase>().todo() }
    single { get<TodoDatabase>().tag() }
    single { get<TodoDatabase>().todoTag() }
    single { TodoLocalRepository(get(), get()) as TodoRepository }
    single { TagLocalRepository(get()) as TagRepository }
    single { ColorLocalRepository() as ColorRepository }
}
