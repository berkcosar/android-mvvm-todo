package info.tuver.todo.ui.todo.todoList

import androidx.databinding.ViewDataBinding
import info.tuver.todo.model.TagModel
import info.tuver.todo.ui.base.BaseAdapterViewHolder
import kotlinx.android.synthetic.main.item_todo_tag_filter.view.*

class TodoTagFilterAdapterViewHolder(viewDataBinding: ViewDataBinding) : BaseAdapterViewHolder<TagModel, TodoTagFilterAdapterActions>(viewDataBinding) {

    override fun onBind(item: TagModel?, adapterActions: TodoTagFilterAdapterActions) {
        itemView.item_todo_tag_filter_chip.setOnCloseIconClickListener {
            item?.let { adapterActions.onRemoveTagFilterButtonClicked(it) }
        }
    }

}