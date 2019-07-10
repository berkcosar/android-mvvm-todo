package info.tuver.todo.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    protected fun launchOnMain(block: suspend CoroutineScope.() -> Unit) {
        coroutineScope.launch(block = block)
    }

    protected fun asyncOnIO(block: suspend CoroutineScope.() -> Unit) {
        asyncOnIO(block, { })
    }

    protected fun asyncOnIO(block: suspend CoroutineScope.() -> Unit, completedBlock: suspend CoroutineScope.() -> Unit) {
        launchOnMain {
            val deferred = async(Dispatchers.IO) {
                block()
            }

            deferred.await()
            completedBlock()
        }
    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }

}