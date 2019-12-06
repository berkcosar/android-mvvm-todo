package info.tuver.todo.ui.todo.todoList

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.ui.base.BaseAdapterViewHolder
import info.tuver.todo.ui.common.FlexSpacingItemDecoration
import info.tuver.todo.ui.common.ItemTouchSwipeToDeleteCallback
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoListAdapterViewHolder(viewDataBinding: ViewDataBinding) : BaseAdapterViewHolder<TodoModel, TodoListAdapterActions>(viewDataBinding), ItemTouchSwipeToDeleteCallback.ItemTouchSwipeToDeleteViewHolder {

    private val todoTagListAdapter = TodoTagListAdapter()

    init {
        itemView.list_todo_tag_recycler.adapter = todoTagListAdapter
        itemView.list_todo_tag_recycler.addItemDecoration(FlexSpacingItemDecoration(itemView.context))
    }

    override val containerView: View
        get() = itemView.item_todo_container_layout

    override fun onBind(item: TodoModel?, adapterActions: TodoListAdapterActions?) {
        item?.let {
            itemView.list_todo_tag_recycler.isVisible = it.tagList.isNotEmpty()
            todoTagListAdapter.updateItemList(it.tagList)
        }
    }

}