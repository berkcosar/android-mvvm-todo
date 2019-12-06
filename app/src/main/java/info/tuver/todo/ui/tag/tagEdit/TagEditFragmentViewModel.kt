package info.tuver.todo.ui.tag.tagEdit

import androidx.databinding.ObservableField
import info.tuver.todo.data.model.TagModel
import info.tuver.todo.data.repository.TagRepository
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TagEditFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val tagRepository: TagRepository, private val tag: TagModel) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    val tagNameValue = ObservableField<String>(tag.name)

    val tagUpdatedEvent = SingleLiveEvent<TagModel>()

    val tagDeletedEvent = SingleLiveEvent<TagModel>()

    fun onUpdateTagButtonClicked() {
        val updatedTagName = tagNameValue.get()
        val updatedTagColor = tag.color

        if (!updatedTagName.isNullOrBlank() && updatedTagColor != null) {
            asyncOnIo(
                { tagRepository.updateTag(tag.id, updatedTagName, updatedTagColor) },
                {
                    tag.apply {
                        name = updatedTagName
                        color = updatedTagColor
                    }

                    tagUpdatedEvent.value = tag
                }
            )
        }
    }

    fun onDeleteTagButtonClicked() {
        asyncOnIo(
            { tagRepository.deleteTag(tag.id) },
            { tagDeletedEvent.value = tag }
        )
    }

}