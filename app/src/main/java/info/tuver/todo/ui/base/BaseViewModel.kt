package info.tuver.todo.ui.base

import androidx.lifecycle.ViewModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) : ViewModel() {

    private val coroutineJob = Job()

    private val coroutineMainScope = CoroutineScope(coroutineDispatcherProvider.mainDispatcher + coroutineJob)

    private val coroutineIoScope = CoroutineScope(coroutineDispatcherProvider.ioDispatcher + coroutineJob)

    protected fun <T> asyncOnIo(block: suspend CoroutineScope.() -> T, completedBlock: suspend CoroutineScope.(result: T) -> Unit = { }) {
        coroutineIoScope.launch {
            val result = block()

            withContext(coroutineMainScope.coroutineContext) {
                completedBlock(result)
            }
        }
    }

    override fun onCleared() {
        coroutineJob.cancel()
        super.onCleared()
    }

}