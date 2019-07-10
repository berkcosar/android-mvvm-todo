package info.tuver.todo.ui.todo.todoList

import android.view.View
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.databinding.ItemTodoBinding
import info.tuver.todo.ui.base.BaseAdapterViewHolder
import info.tuver.todo.ui.common.ItemTouchSwipeToDeleteCallback
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoListAdapterViewHolder(binding: ItemTodoBinding) : BaseAdapterViewHolder<TodoModel, ItemTodoBinding>(binding), ItemTouchSwipeToDeleteCallback.ItemTouchSwipeToDeleteViewHolder {

    override val containerView: View
        get() = itemView.item_todo_container_layout

}