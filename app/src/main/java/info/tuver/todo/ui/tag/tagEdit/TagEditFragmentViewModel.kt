package info.tuver.todo.ui.tag.tagEdit

import androidx.databinding.ObservableField
import info.tuver.todo.domain.TagDomain
import info.tuver.todo.model.ColorModel
import info.tuver.todo.model.TagModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TagEditFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val tagDomain: TagDomain, private val tag: TagModel) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private var selectedColor: String = tag.color

    val tagNameValue = ObservableField<String>(tag.name)

    fun onUpdateTagButtonClicked() {
        val updatedTagName = tagNameValue.get()

        if (!updatedTagName.isNullOrBlank()) {
            asyncOnIo(
                { tagDomain.updateTag(tag, updatedTagName, selectedColor) }
            )
        }
    }

    fun onDeleteTagButtonClicked() {
        asyncOnIo(
            { tagDomain.deleteTag(tag) }
        )
    }

    fun onTagColorSelected(color: ColorModel) {
        selectedColor = color.color
    }

}