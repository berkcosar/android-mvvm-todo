package info.tuver.todo.ui.todo

import androidx.coordinatorlayout.widget.CoordinatorLayout
import info.tuver.todo.R
import info.tuver.todo.databinding.ActivityTodoBinding
import info.tuver.todo.extension.addFragment
import info.tuver.todo.extension.addOnPropertyChangedCallback
import info.tuver.todo.ui.base.BaseActivityView
import info.tuver.todo.ui.todo.todoCreate.TodoCreateFragmentView
import kotlinx.android.synthetic.main.activity_todo.*
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoActivityView : BaseActivityView<TodoActivityViewModel, ActivityTodoBinding>(R.layout.activity_todo) {

    override val coordinatorLayout: CoordinatorLayout
        get() = activity_todo_coordinator_layout

    fun toggleCreateTodoFragment(show: Boolean) {
        when {
            show -> showCreateTodoFragment()
            else -> dismissCreateTodoFragment()
        }
    }

    fun showCreateTodoFragment() {
        addFragment(activity_todo_todo_create_fragment_layout, TodoCreateFragmentView())
    }

    fun dismissCreateTodoFragment() {
        super.onBackPressed()
    }

    override fun createViewModel(): TodoActivityViewModel {
        return getViewModel()
    }

    override fun onSetupView() {
        viewModel.createTodoViewVisibleValue.addOnPropertyChangedCallback { toggleCreateTodoFragment(it) }
    }

    override fun onStartView() {

    }

    override fun onBackPressed() {
        viewModel.onBackButtonClicked()
    }

}
