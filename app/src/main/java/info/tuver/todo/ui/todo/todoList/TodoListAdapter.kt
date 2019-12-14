package info.tuver.todo.ui.todo.todoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import info.tuver.todo.model.TodoModel
import info.tuver.todo.databinding.ItemTodoBinding
import info.tuver.todo.ui.base.BaseAdapter

class TodoListAdapter(todoListAdapterActions: TodoListAdapterActions) : BaseAdapter<TodoModel, TodoListAdapterActions, TodoListAdapterViewHolder>(todoListAdapterActions) {

    override fun createViewHolderBinding(viewType: Int, layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean): ViewDataBinding {
        return ItemTodoBinding.inflate(layoutInflater, parent, attachToRoot)
    }

    override fun createViewHolder(viewType: Int, viewHolderDataBinding: ViewDataBinding): TodoListAdapterViewHolder {
        return TodoListAdapterViewHolder(viewHolderDataBinding)
    }

}