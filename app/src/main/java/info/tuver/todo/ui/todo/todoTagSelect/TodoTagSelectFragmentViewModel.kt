package info.tuver.todo.ui.todo.todoTagSelect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.tuver.todo.data.model.TagModel
import info.tuver.todo.data.model.TagSelectModel
import info.tuver.todo.data.repository.TagRepository
import info.tuver.todo.extension.add
import info.tuver.todo.extension.remove
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoTagSelectFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val tagRepository: TagRepository) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private val mutableSelectedTagListValue = MutableLiveData<List<TagModel>>()

    private val mutableTagSelectListValue = MutableLiveData<List<TagSelectModel>>()

    val showTagCreateViewEvent = SingleLiveEvent<Void>()

    val showTagEditViewEvent = SingleLiveEvent<TagModel>()

    val selectedTagListValue: LiveData<List<TagModel>>
        get() = mutableSelectedTagListValue

    val tagSelectListValue: LiveData<List<TagSelectModel>>
        get() = mutableTagSelectListValue

    fun onLoadTagSelectListRequest() {
        asyncOnIo(
            { tagRepository.getTagList() },
            { mutableTagSelectListValue.postValue(it.map { TagSelectModel(it) }) }
        )
    }

    fun onTagSelectClicked(tagSelect: TagSelectModel) {
        tagSelect.selected = tagSelect.selected.not()

        when {
            tagSelect.selected -> mutableSelectedTagListValue.add(tagSelect.tag)
            else -> mutableSelectedTagListValue.remove(tagSelect.tag)
        }
    }

    fun onTagSelectLongClicked(tagSelect: TagSelectModel) {
        showTagEditViewEvent.value = tagSelect.tag
    }

    fun onCreateNewTagButtonClicked() {
        showTagCreateViewEvent.call()
    }

    fun onTagCreatedEvent(tag: TagModel) {
        mutableTagSelectListValue.add(TagSelectModel(tag, true))
        mutableSelectedTagListValue.add(tag)
    }

}