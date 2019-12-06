package info.tuver.todo.ui.todo.todoCreate

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.databinding.FragmentTodoCreateBinding
import info.tuver.todo.extension.focusAndShowKeyboard
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.todo.todoTagSelect.TodoTagSelectEvents
import kotlinx.android.synthetic.main.fragment_todo_create.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoCreateFragmentView : BaseFragmentView<TodoCreateFragmentViewModel, FragmentTodoCreateBinding>(R.layout.fragment_todo_create, true), TextView.OnEditorActionListener {

    override fun createViewModel(): TodoCreateFragmentViewModel {
        return getViewModel()
    }

    override fun setupView(context: Context) {
        fragment_todo_create_content_edit_text.focusAndShowKeyboard()
        fragment_todo_create_content_edit_text.setOnEditorActionListener(this)

        viewModel.newTodoCreatedEvent.observe(viewLifecycleOwner, Observer { publishEvent(TodoCreateEvents.TodoCreatedEvent(it)) })
    }

    override fun startView(context: Context) {

    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        return when (actionId) {
            EditorInfo.IME_ACTION_SEND -> {
                viewModel.onCreateTodoRequest()
                true
            }
            else -> false
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTodoTagSelectionChangedEvent(tagCreatedEvent: TodoTagSelectEvents.TodoTagSelectionChangedEvent) {
        viewModel.onTodoTagSelectionChangedEvent(tagCreatedEvent.tagList)
    }

}