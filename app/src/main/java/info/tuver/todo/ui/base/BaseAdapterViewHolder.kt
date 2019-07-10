package info.tuver.todo.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import info.tuver.todo.BR

abstract class BaseAdapterViewHolder<TItem, TViewDataBinding : ViewDataBinding>(private val binding: TViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TItem, adapterActions: BaseAdapterActions?) {
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.actions, adapterActions)
        binding.executePendingBindings()
    }

}