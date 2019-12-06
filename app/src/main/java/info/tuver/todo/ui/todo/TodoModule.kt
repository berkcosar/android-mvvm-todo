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
    viewModel { TodoListFragmentViewModel(get(), get()) }
    viewModel { TodoCreateFragmentViewModel(get(), get(), get()) }
    viewModel { TodoTagCreateDialogFragmentViewModel(get()) }
    viewModel { TodoTagEditDialogFragmentViewModel(get()) }
    viewModel { TodoTagSelectFragmentViewModel(get(), get()) }
}