package info.tuver.todo.ui.tag.tagColorSelect

import info.tuver.todo.data.model.ColorSelectModel
import info.tuver.todo.ui.base.BaseAdapterActions

interface TagColorSelectAdapterActions : BaseAdapterActions {

    fun onTagColorSelectClicked(colorSelect: ColorSelectModel)

}