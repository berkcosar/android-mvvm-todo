package info.tuver.todo.ui.todo.todoTagSelect

import androidx.databinding.ViewDataBinding
import info.tuver.todo.model.TagSelectModel
import kotlinx.android.synthetic.main.item_todo_tag_select.view.*

class TodoTagSelectAdapterViewHolderItem(viewDataBinding: ViewDataBinding) : TodoTagSelectAdapterViewHolder(viewDataBinding) {

    override fun onBind(item: TagSelectModel?, adapterActions: TodoTagSelectAdapterActions) {
        itemView.item_todo_tag_select_tag_chip?.setOnLongClickListener {
            item?.let {
                adapterActions.onTodoTagSelectLongClicked(it)
            }

            true
        }
    }

}