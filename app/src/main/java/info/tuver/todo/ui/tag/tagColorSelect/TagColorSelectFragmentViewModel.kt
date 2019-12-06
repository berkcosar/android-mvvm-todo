package info.tuver.todo.ui.tag.tagColorSelect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.tuver.todo.data.model.ColorSelectModel
import info.tuver.todo.data.repository.ColorRepository
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TagColorSelectFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val colorRepository: ColorRepository) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private val mutableColorSelectListValue = MutableLiveData<List<ColorSelectModel>>()

    private var mutableSelectedColorSelect = MutableLiveData<ColorSelectModel>()

    val colorSelectListValue: LiveData<List<ColorSelectModel>>
        get() = mutableColorSelectListValue

    val selectedColorSelectValue: LiveData<ColorSelectModel>
        get() = mutableSelectedColorSelect

    fun loadColorSelectListRequest() {
        asyncOnIo(
            { colorRepository.getColorList() },
            { mutableColorSelectListValue.postValue(it.map { ColorSelectModel(it) }) }
        )
    }

    fun onTagColorSelectClickedEvent(colorSelect: ColorSelectModel) {
        mutableSelectedColorSelect.postValue(colorSelect)
    }

}