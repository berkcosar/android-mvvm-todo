package info.tuver.todo.ui.todo

import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.databinding.ActivityTodoBinding
import info.tuver.todo.extension.addFragment
import info.tuver.todo.extension.replaceFragment
import info.tuver.todo.ui.base.BaseActivityView
import info.tuver.todo.ui.todo.todoCreate.TodoCreateFragmentView
import info.tuver.todo.ui.todo.todoList.TodoListFragmentView
import kotlinx.android.synthetic.main.activity_todo.*
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoActivityView : BaseActivityView<TodoActivityViewModel, ActivityTodoBinding>(R.layout.activity_todo) {

    override val coordinatorLayout: CoordinatorLayout
        get() = activity_todo_coordinator_layout

    private fun showTodoCreateFragment() {
        addFragment(activity_todo_fragment_layout, TodoCreateFragmentView())
    }

    override fun createViewModel(): TodoActivityViewModel {
        return getViewModel()
    }

    override fun setupView() {
        viewModel.showTodoCreateViewEvent.observe(this, Observer { showTodoCreateFragment() })
    }

    override fun startView() {
        replaceFragment(activity_todo_fragment_layout, TodoListFragmentView())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.onBackButtonClicked()
    }

}
