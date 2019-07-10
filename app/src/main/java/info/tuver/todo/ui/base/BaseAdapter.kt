package info.tuver.todo.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import info.tuver.todo.data.model.BaseModel

abstract class BaseAdapter<TItem : BaseModel, TViewHolderDataBinding : ViewDataBinding, TViewHolder : BaseAdapterViewHolder<TItem, TViewHolderDataBinding>>(private val adapterActions: BaseAdapterActions? = null) :
    RecyclerView.Adapter<TViewHolder>() {

    private var itemList = mutableListOf<TItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TViewHolder {
        val viewHolderBinding = createViewHolderBinding(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return createViewHolder(viewHolderBinding)
    }

    abstract fun createViewHolderBinding(layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean): TViewHolderDataBinding

    abstract fun createViewHolder(viewHolderDataBinding: TViewHolderDataBinding): TViewHolder

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TViewHolder, position: Int) {
        holder.bind(itemList.get(position), adapterActions)
    }

    fun getItemAtPosition(position: Int): TItem {
        return itemList.get(position)
    }

    fun updateItemList(newItemList: List<TItem>) {
        val diffCallback = BaseAdapterDiffCallback(itemList, newItemList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        itemList.clear()
        itemList.addAll(newItemList)

        diffResult.dispatchUpdatesTo(this)
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

}