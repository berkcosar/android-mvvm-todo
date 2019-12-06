package info.tuver.todo.ui.tag.tagCreate

import androidx.databinding.ObservableField
import info.tuver.todo.data.model.ColorModel
import info.tuver.todo.data.model.TagModel
import info.tuver.todo.data.repository.TagRepository
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TagCreateFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val tagRepository: TagRepository) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private var selectedColor: ColorModel? = null

    val newTagNameValue = ObservableField<String>()

    val newTagCreatedEvent = SingleLiveEvent<TagModel>()

    fun onSaveTagButtonClicked() {
        val newTagName = newTagNameValue.get()
        val newTagColor = selectedColor?.color

        if (!newTagName.isNullOrBlank() && newTagColor != null) {
            asyncOnIo(
                { tagRepository.createTag(newTagName, newTagColor) },
                { newTagCreatedEvent.value = it }
            )
        }
    }

    fun onTagColorSelectedEvent(color: ColorModel) {
        selectedColor = color
    }

}