package info.tuver.todo.ui.todo.todoTagSelect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.tuver.todo.domain.TagDomain
import info.tuver.todo.extension.add
import info.tuver.todo.extension.remove
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.model.TagModel
import info.tuver.todo.model.TagSelectModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TodoTagSelectFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val tagDomain: TagDomain) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private val mutableSelectedTagListValue = MutableLiveData<List<TagModel>>()

    private val mutableTagSelectListValue = MutableLiveData<List<TagSelectModel>>()

    val showTagCreateViewEvent = SingleLiveEvent<Void>()

    val showTagEditViewEvent = SingleLiveEvent<TagModel>()

    val tagSelectUpdatedEvent = SingleLiveEvent<TagSelectModel>()

    val selectedTagListValue: LiveData<List<TagModel>>
        get() = mutableSelectedTagListValue

    val tagSelectListValue: LiveData<List<TagSelectModel>>
        get() = mutableTagSelectListValue

    init {
        subscribe(tagDomain.tagCreatedSubject) { onTagCreated(it) }
        subscribe(tagDomain.tagUpdatedSubject) { onTagUpdated(it) }
        subscribe(tagDomain.tagDeletedSubject) { onTagDeleted(it) }
    }

    private fun onTagCreated(tag: TagModel) {
        mutableTagSelectListValue.add(TagSelectModel(tag, true))
        mutableSelectedTagListValue.add(tag)
    }

    private fun onTagUpdated(tag: TagModel) {
        mutableTagSelectListValue.value?.find { it.idCode == tag.idCode }?.let { tagSelect ->
            tagSelectUpdatedEvent.postValue(tagSelect)
        }
    }

    private fun onTagDeleted(tag: TagModel) {
        mutableSelectedTagListValue.remove(tag)
        mutableTagSelectListValue.remove(mutableTagSelectListValue.value?.find { it.tag == tag })
    }

    fun onLoadTagSelectListRequest() {
        asyncOnIo(
            { tagDomain.getTagSelectList() },
            { mutableTagSelectListValue.postValue(it) }
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

}