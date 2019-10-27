package info.tuver.todo.ui.todo

import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.databinding.ActivityTodoBinding
import info.tuver.todo.extension.addFragment
import info.tuver.todo.extension.replaceFragment
import info.tuver.todo.ui.base.BaseActivity
import info.tuver.todo.ui.todo.todoCreate.TodoCreateFragment
import info.tuver.todo.ui.todo.todoList.TodoListFragment
import kotlinx.android.synthetic.main.activity_todo.*
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoActivity : BaseActivity<TodoActivityViewModel, ActivityTodoBinding>(R.layout.activity_todo) {

    override fun initView() {
        replaceFragment(activity_todo_fragment_layout, TodoListFragment())
    }

    override fun setupView() {
        viewModel.showTodoCreateViewEvent.observe(this, Observer { showTodoCreateFragment() })
    }

    override fun createViewModel(): TodoActivityViewModel {
        return getViewModel()
    }

    private fun showTodoCreateFragment() {
        addFragment(activity_todo_fragment_layout, TodoCreateFragment())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.onBackButtonClicked()
    }

}
