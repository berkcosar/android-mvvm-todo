package info.tuver.todo.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import info.tuver.todo.BR

abstract class BaseAdapterViewHolder<TItem, TAdapterActions : BaseAdapterActions>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    protected open fun onBind(item: TItem?, adapterActions: TAdapterActions?) {

    }

    fun bind(item: TItem?, adapterActions: TAdapterActions?) {
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.actions, adapterActions)
        binding.executePendingBindings()

        onBind(item, adapterActions)
    }

}