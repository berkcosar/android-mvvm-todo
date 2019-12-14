package info.tuver.todo.ui.todo.todoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import info.tuver.todo.model.TagModel
import info.tuver.todo.databinding.ItemTodoTagBinding
import info.tuver.todo.ui.base.BaseAdapter

class TodoTagListAdapter(todoTagListAdapterActions: TodoTagListAdapterActions) : BaseAdapter<TagModel, TodoTagListAdapterActions, TodoTagListAdapterViewHolder>(todoTagListAdapterActions) {

    override fun createViewHolderBinding(viewType: Int, layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean): ViewDataBinding {
        return ItemTodoTagBinding.inflate(layoutInflater, parent, attachToRoot)
    }

    override fun createViewHolder(viewType: Int, viewHolderDataBinding: ViewDataBinding): TodoTagListAdapterViewHolder {
        return TodoTagListAdapterViewHolder(viewHolderDataBinding)
    }

}