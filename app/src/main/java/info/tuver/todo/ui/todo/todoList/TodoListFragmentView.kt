package info.tuver.todo.ui.todo.todoList

import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import info.tuver.todo.R
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.databinding.FragmentTodoListBinding
import info.tuver.todo.extension.addItemTouchHelper
import info.tuver.todo.extension.showSnackbar
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.common.ItemTouchSwipeToDeleteCallback
import info.tuver.todo.ui.todo.todoCreate.TodoCreateEvents
import kotlinx.android.synthetic.main.fragment_todo_list.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoListFragmentView : BaseFragmentView<TodoListFragmentViewModel, FragmentTodoListBinding>(R.layout.fragment_todo_list, true), TodoListAdapterActions,
    ItemTouchSwipeToDeleteCallback.ItemTouchSwipeToDeleteCallbackListener {

    private val todoListAdapter = TodoListAdapter(this)

    private fun updateTodoList(todoList: List<TodoModel>) {
        todoListAdapter.updateItemList(todoList)
    }

    private fun updateTodo(todo: TodoModel) {
        todoListAdapter.notifyItemChanged(todo)
    }

    private fun showTodoDeletedToast() {
        showSnackbar(R.string.todo_deleted, R.string.todo_delete_undo, { viewModel.onUndoDeleteTodoRequest() })
    }

    override fun createViewModel(): TodoListFragmentViewModel {
        return getViewModel()
    }

    override fun setupView(context: Context) {
        fragment_todo_list_recycler.adapter = todoListAdapter
        fragment_todo_list_recycler.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        fragment_todo_list_recycler.addItemTouchHelper(ItemTouchSwipeToDeleteCallback(this))

        viewModel.todoListValue.observe(viewLifecycleOwner, Observer { updateTodoList(it) })
        viewModel.todoUpdatedEvent.observe(viewLifecycleOwner, Observer { updateTodo(it) })
        viewModel.todoDeletedEvent.observe(viewLifecycleOwner, Observer { showTodoDeletedToast() })
    }

    override fun startView(context: Context) {
        viewModel.onLoadTodoListRequest()
    }

    override fun onItemSwipedToDelete(position: Int) {
        viewModel.onDeleteTodoRequest(position)
    }

    override fun onTodoCompletedCheckboxValueChanged(todo: TodoModel, checked: Boolean) {
        viewModel.onUpdateTodoCompletedValueRequest(todo, checked)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTodoCreatedEvent(todoCreatedEvent: TodoCreateEvents.TodoCreatedEvent) {
        viewModel.onTodoCreatedEvent(todoCreatedEvent.todo)
    }

}