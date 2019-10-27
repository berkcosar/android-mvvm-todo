package info.tuver.todo.provider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherMainProvider : CoroutineDispatcherProvider {

    override val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    override val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

}