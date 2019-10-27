package info.tuver.todo.ui.todo

import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import info.tuver.todo.extension.once
import info.tuver.todo.extension.twice
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseViewModelTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TodoActivityViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: TodoActivityViewModel

    private val addTodoButtonVisibleValueOnPropertyChangedCallback: OnPropertyChangedCallback = mock()

    private val showTodoCreateViewEventObserver: Observer<Any> = mock()

    override fun onSetup(coroutineDispatcherProvider: CoroutineDispatcherProvider) {
        viewModel = TodoActivityViewModel(coroutineDispatcherProvider).apply {
            addTodoButtonVisibleValue.addOnPropertyChangedCallback(addTodoButtonVisibleValueOnPropertyChangedCallback)
            showTodoCreateViewEvent.observeForever(showTodoCreateViewEventObserver)
        }
    }

    @Test
    fun `should hide add todo button and call show create view event when add todo button clicked`() {
        val addTodoButtonVisibleValueCaptor = argumentCaptor<ObservableBoolean>()

        viewModel.onAddTodoButtonClicked()

        verify(addTodoButtonVisibleValueOnPropertyChangedCallback, once()).onPropertyChanged(addTodoButtonVisibleValueCaptor.capture(), any())
        verify(showTodoCreateViewEventObserver, once()).onChanged(anyOrNull())
        assertEquals(false, addTodoButtonVisibleValueCaptor.lastValue.get())
    }

    @Test
    fun `should show add todo button if it is hidden when back button clicked`() {
        val addTodoButtonVisibleValueCaptor = argumentCaptor<ObservableBoolean>()

        viewModel.onAddTodoButtonClicked()
        viewModel.onBackButtonClicked()

        verify(addTodoButtonVisibleValueOnPropertyChangedCallback, twice()).onPropertyChanged(addTodoButtonVisibleValueCaptor.capture(), any())
        assertEquals(true, addTodoButtonVisibleValueCaptor.lastValue.get())
    }

    @Test
    fun `should not show add todo button if it is not hidden when back button clicked`() {
        viewModel.onBackButtonClicked()

        verify(addTodoButtonVisibleValueOnPropertyChangedCallback, never()).onPropertyChanged(anyOrNull(), any())
    }

}