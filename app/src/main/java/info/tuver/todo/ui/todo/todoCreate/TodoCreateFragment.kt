package info.tuver.todo.ui.todo.todoCreate

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.databinding.FragmentTodoCreateBinding
import info.tuver.todo.extension.focusAndShowKeyboard
import info.tuver.todo.ui.base.BaseFragment
import info.tuver.todo.ui.todo.TodoCreatedEvent
import kotlinx.android.synthetic.main.fragment_todo_create.*
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoCreateFragment : BaseFragment<TodoCreateFragmentViewModel, FragmentTodoCreateBinding>(R.layout.fragment_todo_create), TextView.OnEditorActionListener {

    private fun onTodoCreated(todo: TodoModel) {
        publishEvent(TodoCreatedEvent(todo))
    }

    override fun createViewModel(): TodoCreateFragmentViewModel {
        return getViewModel()
    }

    override fun initView(context: Context) {
        fragment_todo_create_content_edit_text.focusAndShowKeyboard()
        fragment_todo_create_content_edit_text.setOnEditorActionListener(this)

        viewModel.newTodoCreatedEvent.observe(viewLifecycleOwner, Observer { onTodoCreated(it) })
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