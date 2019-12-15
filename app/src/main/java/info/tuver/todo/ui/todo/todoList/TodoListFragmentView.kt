package info.tuver.todo.ui.todo.todoList

import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import info.tuver.todo.R
import info.tuver.todo.databinding.FragmentTodoListBinding
import info.tuver.todo.extension.addItemTouchHelper
import info.tuver.todo.extension.setCoordinatorLayoutBehavior
import info.tuver.todo.extension.showSnackbar
import info.tuver.todo.model.TagModel
import info.tuver.todo.model.TodoModel
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.common.ControllableAppBarLayoutBehavior
import info.tuver.todo.ui.common.ItemTouchSwipeToDeleteCallback
import info.tuver.todo.ui.common.SpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_todo_list.*
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoListFragmentView : BaseFragmentView<TodoListFragmentViewModel, FragmentTodoListBinding>(R.layout.fragment_todo_list), TodoListAdapterActions, TodoTagFilterAdapterActions,
    ItemTouchSwipeToDeleteCallback.ItemTouchSwipeToDeleteCallbackListener {

    private val todoListAdapter = TodoListAdapter(this)

    private val todoTagFilterAdapter = TodoTagFilterAdapter(this)

    private fun updateTodoList(todoList: List<TodoModel>) {
        todoListAdapter.updateItemList(todoList)
    }

    private fun updateTodo(todo: TodoModel) {
        todoListAdapter.notifyItemChanged(todo)
    }

    private fun showTodoDeletedToast() {
        showSnackbar(R.string.todo_deleted, R.string.todo_delete_undo, { viewModel.onUndoDeleteTodoRequest() })
    }

    private fun updateTagFilterList(tagFilterList: List<TagModel>) {
        when (tagFilterList.size) {
            0 -> disableAppBarAndCollapse()
            else -> enableAppBarAndExpand()
        }

        todoTagFilterAdapter.updateItemList(tagFilterList)
    }

    private fun disableAppBarAndCollapse() {
        fragment_todo_list_app_bar_layout.setExpanded(false, true)
    }

    private fun enableAppBarAndExpand() {
        fragment_todo_list_app_bar_layout.setExpanded(true, true)
    }

    override fun createViewModel(): TodoListFragmentViewModel {
        return getViewModel()
    }

    override fun onSetupView(context: Context) {
        fragment_todo_list_recycler.adapter = todoListAdapter
        fragment_todo_list_recycler.addItemDecoration(DividerItemDecoration(context, VERTICAL))
        fragment_todo_list_recycler.addItemTouchHelper(ItemTouchSwipeToDeleteCallback(this))
        fragment_todo_list_tag_filter_recycler.adapter = todoTagFilterAdapter
        fragment_todo_list_tag_filter_recycler.addItemDecoration(SpacingItemDecoration(context))
        fragment_todo_list_app_bar_layout.setExpanded(false, false)
        fragment_todo_list_app_bar_layout.setCoordinatorLayoutBehavior(ControllableAppBarLayoutBehavior(false, context))

        viewModel.todoListValue.observe(viewLifecycleOwner, Observer { updateTodoList(it) })
        viewModel.todoUpdatedEvent.observe(viewLifecycleOwner, Observer { updateTodo(it) })
        viewModel.todoDeletedEvent.observe(viewLifecycleOwner, Observer { showTodoDeletedToast() })
        viewModel.selectedTagListValue.observe(viewLifecycleOwner, Observer { updateTagFilterList(it) })
    }

    override fun onStartView(context: Context) {
        viewModel.onLoadTodoListRequest()
    }

    override fun onItemSwipedToDelete(position: Int) {
        viewModel.onDeleteTodoRequest(position)
    }

    override fun onTodoCompletedCheckboxValueChanged(todo: TodoModel, checked: Boolean) {
        viewModel.onUpdateTodoCompletedValueRequest(todo, checked)
    }

    override fun onTodoTagClicked(tag: TagModel) {
        viewModel.onTodoTagClicked(tag)
    }

    override fun onRemoveTagFilterButtonClicked(tag: TagModel) {
        viewModel.onRemoveTagFilterButtonClicked(tag)
    }

}