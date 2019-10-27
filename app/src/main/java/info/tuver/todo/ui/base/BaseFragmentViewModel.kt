package info.tuver.todo.ui.base

import info.tuver.todo.provider.CoroutineDispatcherProvider

abstract class BaseFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) : BaseViewModel(coroutineDispatcherProvider)