package info.tuver.todo.ui.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.provider.CoroutineDispatcherProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider) : ViewModel() {

    private val coroutineJob = Job()

    private val coroutineMainScope = CoroutineScope(coroutineDispatcherProvider.mainDispatcher + coroutineJob)

    private val coroutineIoScope = CoroutineScope(coroutineDispatcherProvider.ioDispatcher + coroutineJob)

    private val compositeDisposable = CompositeDisposable()

    val completedEvent = SingleLiveEvent<Void>()

    protected fun <T> asyncOnIo(block: suspend CoroutineScope.() -> T, completedBlock: suspend CoroutineScope.(result: T) -> Unit = { }) {
        coroutineIoScope.launch {
            val result = block()

            withContext(coroutineMainScope.coroutineContext) {
                completedBlock(result)
            }
        }
    }

    protected fun <T> subscribe(observable: Observable<T>, block: (T) -> Unit) {
        compositeDisposable.add(
            observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(block)
        )
    }

    @CallSuper
    override fun onCleared() {
        compositeDisposable.clear()
        coroutineJob.cancel()

        super.onCleared()
    }

}