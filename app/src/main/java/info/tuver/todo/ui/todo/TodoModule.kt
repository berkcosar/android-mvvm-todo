package info.tuver.todo.ui.todo

import info.tuver.todo.ui.todo.todoCreate.TodoCreateFragmentViewModel
import info.tuver.todo.ui.todo.todoList.TodoListFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoModule = module {
    viewModel { TodoActivityViewModel(get()) }
    viewModel { TodoListFragmentViewModel(get(), get()) }
    viewModel { TodoCreateFragmentViewModel(get(), get()) }
}