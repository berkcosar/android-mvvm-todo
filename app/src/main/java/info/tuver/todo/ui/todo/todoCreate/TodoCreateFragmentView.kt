package info.tuver.todo.ui.todo.todoCreate

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.databinding.FragmentTodoCreateBinding
import info.tuver.todo.extension.addOnPropertyChangedCallback
import info.tuver.todo.extension.focusAndShowKeyboard
import info.tuver.todo.extension.getFragment
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.todo.todoTagSelect.TodoTagSelectFragmentView
import kotlinx.android.synthetic.main.fragment_todo_create.*
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoCreateFragmentView : BaseFragmentView<TodoCreateFragmentViewModel, FragmentTodoCreateBinding>(R.layout.fragment_todo_create), TextView.OnEditorActionListener {

    private val todoTagSelectFragmentView: TodoTagSelectFragmentView
        get() = getFragment(R.id.fragment_todo_create_tag_select_fragment)

    override fun createViewModel(): TodoCreateFragmentViewModel {
        return getViewModel()
    }

    override fun setupView(context: Context) {
        fragment_todo_create_content_edit_text.focusAndShowKeyboard()
        fragment_todo_create_content_edit_text.setOnEditorActionListener(this)
        todoTagSelectFragmentView.selectedTagList.addOnPropertyChangedCallback { viewModel.onTodoTagSelectionChanged(it) }

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

}