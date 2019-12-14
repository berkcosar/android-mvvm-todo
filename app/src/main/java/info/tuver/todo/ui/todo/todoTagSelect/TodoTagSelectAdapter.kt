package info.tuver.todo.ui.todo.todoTagSelect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import info.tuver.todo.model.TagSelectModel
import info.tuver.todo.databinding.ItemTodoTagSelectBinding
import info.tuver.todo.databinding.ItemTodoTagSelectCreateBinding
import info.tuver.todo.ui.base.BaseAdapter

class TodoTagSelectAdapter(todoTagSelectAdapterActions: TodoTagSelectAdapterActions) : BaseAdapter<TagSelectModel, TodoTagSelectAdapterActions, TodoTagSelectAdapterViewHolder>(todoTagSelectAdapterActions) {

    override fun createViewHolderBinding(viewType: Int, layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean): ViewDataBinding {
        return when (viewType) {
            ITEM_VIEW_TYPE_FOOTER -> ItemTodoTagSelectCreateBinding.inflate(layoutInflater, parent, attachToRoot)
            else -> ItemTodoTagSelectBinding.inflate(layoutInflater, parent, attachToRoot)
        }
    }

    override fun createViewHolder(viewType: Int, viewHolderDataBinding: ViewDataBinding): TodoTagSelectAdapterViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_FOOTER -> TodoTagSelectAdapterViewHolderFooter(viewHolderDataBinding)
            else -> TodoTagSelectAdapterViewHolderItem(viewHolderDataBinding)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            itemCount - 1 -> ITEM_VIEW_TYPE_FOOTER
            else -> super.getItemViewType(position)
        }
    }

    override fun getItem(position: Int): TagSelectModel? {
        return when (position) {
            itemCount - 1 -> null
            else -> super.getItem(position)
        }
    }

    companion object {

        private const val ITEM_VIEW_TYPE_FOOTER = 1

    }

}