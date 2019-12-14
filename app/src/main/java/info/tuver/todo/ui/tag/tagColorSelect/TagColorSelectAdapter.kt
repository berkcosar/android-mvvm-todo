package info.tuver.todo.ui.tag.tagColorSelect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import info.tuver.todo.model.ColorSelectModel
import info.tuver.todo.databinding.ItemTagColorSelectBinding
import info.tuver.todo.ui.base.BaseAdapter

class TagColorSelectAdapter(tagColorSelectAdapterActions: TagColorSelectAdapterActions) : BaseAdapter<ColorSelectModel, TagColorSelectAdapterActions, TagColorSelectAdapterViewHolder>(tagColorSelectAdapterActions) {

    override fun createViewHolderBinding(viewType: Int, layoutInflater: LayoutInflater, parent: ViewGroup, attachToRoot: Boolean): ViewDataBinding {
        return ItemTagColorSelectBinding.inflate(layoutInflater, parent, attachToRoot)
    }

    override fun createViewHolder(viewType: Int, viewHolderDataBinding: ViewDataBinding): TagColorSelectAdapterViewHolder {
        return TagColorSelectAdapterViewHolder(viewHolderDataBinding)
    }

}