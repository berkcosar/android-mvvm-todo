package info.tuver.todo.ui.tag.tagColorSelect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.tuver.todo.domain.ColorDomain
import info.tuver.todo.model.ColorSelectModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TagColorSelectFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val colorDomain: ColorDomain) : BaseFragmentViewModel(coroutineDispatcherProvider) {

    private val mutableColorSelectListValue = MutableLiveData<List<ColorSelectModel>>()

    private var mutableSelectedColorSelect = MutableLiveData<ColorSelectModel>()

    val colorSelectListValue: LiveData<List<ColorSelectModel>>
        get() = mutableColorSelectListValue

    val selectedColorSelectValue: LiveData<ColorSelectModel>
        get() = mutableSelectedColorSelect

    fun loadColorSelectListRequest() {
        asyncOnIo(
            { colorDomain.getColorSelectList() },
            { mutableColorSelectListValue.postValue(it) }
        )
    }

    fun onTagColorSelectClicked(colorSelect: ColorSelectModel) {
        mutableSelectedColorSelect.postValue(colorSelect)
    }

}