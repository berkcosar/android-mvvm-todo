package info.tuver.todo.ui.tag.tagCreate

import androidx.databinding.ObservableField
import info.tuver.todo.domain.TagDomain
import info.tuver.todo.extension.STRING_EMPTY
import info.tuver.todo.model.ColorModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TagCreateFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val tagDomain: TagDomain) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private var selectedColor: ColorModel? = null

    val newTagNameValue = ObservableField<String>()

    fun onSaveTagButtonClicked() {
        val newTagName = newTagNameValue.get()
        val newTagColor = selectedColor?.color

        if (!newTagName.isNullOrBlank() && newTagColor != null) {
            asyncOnIo(
                { tagDomain.createTag(newTagName, newTagColor) },
                { newTagNameValue.set(STRING_EMPTY) }
            )
        }
    }

    fun onTagColorSelected(color: ColorModel) {
        selectedColor = color
    }

}