package info.tuver.todo.ui.todo.todoCreate

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import info.tuver.todo.R
import info.tuver.todo.databinding.FragmentTodoCreateBinding
import info.tuver.todo.extension.focusAndShowKeyboard
import info.tuver.todo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_todo_create.*
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoCreateFragment : BaseFragment<TodoCreateViewModel, FragmentTodoCreateBinding>(R.layout.fragment_todo_create), TextView.OnEditorActionListener {

    override fun createViewModel(): TodoCreateViewModel {
        return getViewModel()
    }

    override fun initView(context: Context) {
        fragment_todo_create_content_edit_text.focusAndShowKeyboard(context)
        fragment_todo_create_content_edit_text.setOnEditorActionListener(this)
    }

    override fun restoreView(context: Context, savedInstanceState: Bundle) {
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        var handled = false

        if (actionId == EditorInfo.IME_ACTION_SEND) {
            viewModel.onCreateTodoRequest()
            handled = true
        }

        return handled
    }

}