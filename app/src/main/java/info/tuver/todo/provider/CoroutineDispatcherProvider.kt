package info.tuver.todo.provider

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {

    val mainDispatcher: CoroutineDispatcher

    val ioDispatcher: CoroutineDispatcher

}