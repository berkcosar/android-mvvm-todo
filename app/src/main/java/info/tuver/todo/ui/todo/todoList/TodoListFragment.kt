package info.tuver.todo.ui.todo.todoList

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import info.tuver.todo.R
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.databinding.FragmentTodoListBinding
import info.tuver.todo.extension.addItemTouchHelper
import info.tuver.todo.ui.base.BaseFragment
import info.tuver.todo.ui.common.ItemTouchSwipeToDeleteCallback
import kotlinx.android.synthetic.main.fragment_todo_list.*
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoListFragment : BaseFragment<TodoListFragmentViewModel, FragmentTodoListBinding>(R.layout.fragment_todo_list), TodoListAdapterActions, ItemTouchSwipeToDeleteCallback.ItemTouchSwipeToDeleteCallbackListener {

    private val todoListAdapter = TodoListAdapter(this)

    override fun createViewModel(): TodoListFragmentViewModel {
        return getViewModel()
    }

    override fun initView(context: Context) {
        fragment_todo_list_recycler.adapter = todoListAdapter
        fragment_todo_list_recycler.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        fragment_todo_list_recycler.addItemTouchHelper(ItemTouchSwipeToDeleteCallback(this))

        viewModel.todoList.observe(viewLifecycleOwner, Observer { updateTodoList(it) })
        viewModel.onRefreshTodoListRequest()
    }

    private fun updateTodoList(todoList: List<TodoModel>) {
        todoListAdapter.updateItemList(todoList)
    }

    override fun restoreView(context: Context, savedInstanceState: Bundle) {

    }

    override fun onItemSwipedToDelete(position: Int) {
        viewModel.onDeleteTodoRequest(position)
    }

    override fun onItemCompletedCheckboxValueChanged(todo: TodoModel, checked: Boolean) {
        viewModel.onUpdateTodoCompletedValueRequest(todo, checked)
    }

}