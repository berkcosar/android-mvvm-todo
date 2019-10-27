package info.tuver.todo.ui.base

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import info.tuver.todo.base.BaseTest
import info.tuver.todo.provider.CoroutineDispatcherProvider
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModelTest : BaseTest() {

    private val coroutineDispatcherProvider: CoroutineDispatcherProvider = mock()

    override fun onSetup() {
        whenever(coroutineDispatcherProvider.mainDispatcher).doReturn(Dispatchers.Unconfined)
        whenever(coroutineDispatcherProvider.ioDispatcher).doReturn(Dispatchers.Unconfined)

        onSetup(coroutineDispatcherProvider)
    }

    protected abstract fun onSetup(coroutineDispatcherProvider: CoroutineDispatcherProvider)

}