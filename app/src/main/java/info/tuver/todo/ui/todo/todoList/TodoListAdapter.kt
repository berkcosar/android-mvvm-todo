package info.tuver.todo.ui.todo.todoList

import android.view.LayoutInflater
import android.view.ViewGroup
import info.tuver.todo.data.model.TodoModel
import info.tuver.todo.databinding.ItemTodoBinding
import info.tuver.todo.ui.base.BaseAdapter

class TodoListAdapter(todoListAdapterActions: TodoListAdapterActions) : BaseAdapter<TodoModel, ItemTodoBinding, TodoListAdapterViewHolder>(todoListAdapterActions) {

    override fun createViewHolderBinding(layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean): ItemTodoBinding {
        return ItemTodoBinding.inflate(layoutInflater, parent, attachToRoot)
    }

    override fun createViewHolder(viewHolderDataBinding: ItemTodoBinding): TodoListAdapterViewHolder {
        return TodoListAdapterViewHolder(viewHolderDataBinding)
    }

}