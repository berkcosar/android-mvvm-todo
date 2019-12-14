package info.tuver.todo.ui.base

import androidx.recyclerview.widget.DiffUtil
import info.tuver.todo.model.BaseModel

class BaseAdapterDiffCallback<T : BaseModel>(private val oldItemList: List<T>, private val newItemList: List<T>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemList.get(oldItemPosition).idCode == newItemList.get(newItemPosition).idCode
    }

    override fun getOldListSize(): Int {
        return oldItemList.size
    }

    override fun getNewListSize(): Int {
        return newItemList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemList.get(oldItemPosition).equals(newItemList.get(newItemPosition))
    }

}