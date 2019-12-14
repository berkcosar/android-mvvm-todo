package info.tuver.todo.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import info.tuver.todo.model.BaseModel

abstract class BaseAdapter<TItem : BaseModel, TAdapterActions : BaseAdapterActions, TViewHolder : BaseAdapterViewHolder<TItem, TAdapterActions>>(private val adapterActions: TAdapterActions) :
    RecyclerView.Adapter<TViewHolder>() {

    private var itemList = mutableListOf<TItem>()

    abstract fun createViewHolderBinding(viewType: Int, layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean): ViewDataBinding

    abstract fun createViewHolder(viewType: Int, viewHolderDataBinding: ViewDataBinding): TViewHolder

    protected open fun getItem(position: Int): TItem? {
        return itemList.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TViewHolder {
        val viewHolderBinding = createViewHolderBinding(
            viewType,
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return createViewHolder(viewType, viewHolderBinding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TViewHolder, position: Int) {
        holder.bind(getItem(position), adapterActions)
    }

    fun setItemList(newItemList: List<TItem>) {
        itemList.clear()
        itemList.addAll(newItemList)

        notifyDataSetChanged()
    }

    fun updateItemList(newItemList: List<TItem>) {
        when {
            itemList.isEmpty() -> setItemList(newItemList)
            else -> {
                val diffCallback = BaseAdapterDiffCallback(itemList, newItemList)
                val diffResult = DiffUtil.calculateDiff(diffCallback)

                itemList.clear()
                itemList.addAll(newItemList)

                diffResult.dispatchUpdatesTo(this)
            }
        }
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun notifyItemChanged(item: TItem) {
        notifyItemChanged(itemList.indexOf(item))
    }

}