package info.tuver.todo.ui.base

import info.tuver.todo.provider.CoroutineDispatcherProvider

abstract class BaseActivityViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) : BaseViewModel(coroutineDispatcherProvider)