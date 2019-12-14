package info.tuver.todo.ui.todo

import info.tuver.todo.ui.todo.todoCreate.TodoCreateFragmentViewModel
import info.tuver.todo.ui.todo.todoList.TodoListFragmentViewModel
import info.tuver.todo.ui.todo.todoTagCreateDialog.TodoTagCreateDialogFragmentViewModel
import info.tuver.todo.ui.todo.todoTagEditDialog.TodoTagEditDialogFragmentViewModel
import info.tuver.todo.ui.todo.todoTagSelect.TodoTagSelectFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoModule = module {
    viewModel { TodoActivityViewModel(get()) }
    viewModel { TodoListFragmentViewModel(get(), get(), get()) }
    viewModel { TodoCreateFragmentViewModel(get(), get()) }
    viewModel { TodoTagCreateDialogFragmentViewModel(get(), get()) }
    viewModel { TodoTagEditDialogFragmentViewModel(get(), get()) }
    viewModel { TodoTagSelectFragmentViewModel(get(), get()) }
}