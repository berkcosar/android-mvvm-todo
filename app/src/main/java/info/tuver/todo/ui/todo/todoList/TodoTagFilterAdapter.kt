package info.tuver.todo.ui.todo.todoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import info.tuver.todo.databinding.ItemTodoTagFilterBinding
import info.tuver.todo.model.TagModel
import info.tuver.todo.ui.base.BaseAdapter

class TodoTagFilterAdapter(todoTagFilterAdapterActions: TodoTagFilterAdapterActions) : BaseAdapter<TagModel, TodoTagFilterAdapterActions, TodoTagFilterAdapterViewHolder>(todoTagFilterAdapterActions) {

    override fun createViewHolderBinding(viewType: Int, layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean): ViewDataBinding {
        return ItemTodoTagFilterBinding.inflate(layoutInflater, parent, attachToRoot)
    }

    override fun createViewHolder(viewType: Int, viewHolderDataBinding: ViewDataBinding): TodoTagFilterAdapterViewHolder {
        return TodoTagFilterAdapterViewHolder(viewHolderDataBinding)
    }

}