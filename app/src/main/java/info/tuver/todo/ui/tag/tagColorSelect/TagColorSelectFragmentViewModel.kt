package info.tuver.todo.ui.tag.tagColorSelect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import info.tuver.todo.domain.ColorDomain
import info.tuver.todo.external.SingleLiveEvent
import info.tuver.todo.model.ColorSelectModel
import info.tuver.todo.provider.CoroutineDispatcherProvider
import info.tuver.todo.ui.base.BaseFragmentViewModel

class TagColorSelectFragmentViewModel(coroutineDispatcherProvider: CoroutineDispatcherProvider, private val colorDomain: ColorDomain, private val preSelectedColor: String?) :
    BaseFragmentViewModel(coroutineDispatcherProvider) {

    private var selectedColorSelect: ColorSelectModel? = null

    private val mutableColorSelectListValue = MutableLiveData<List<ColorSelectModel>>()

    private val mutablePreSelectedColorSelect = MutableLiveData<ColorSelectModel>()

    val colorSelectSelectedEvent = SingleLiveEvent<ColorSelectModel>()

    val colorSelectUnSelectedEvent = SingleLiveEvent<ColorSelectModel>()

    val colorSelectListValue: LiveData<List<ColorSelectModel>>
        get() = mutableColorSelectListValue

    val preSelectedColorSelect: LiveData<ColorSelectModel>
        get() = mutablePreSelectedColorSelect

    private fun unSelectColorSelect(colorSelect: ColorSelectModel) {
        asyncOnIo(
            { colorDomain.updateColorSelectSelected(colorSelect, false) },
            { colorSelectUnSelectedEvent.postValue(colorSelect) }
        )
    }

    private fun selectColorSelect(colorSelect: ColorSelectModel) {
        asyncOnIo(
            { colorDomain.updateColorSelectSelected(colorSelect, true) },
            { colorSelectSelectedEvent.postValue(colorSelect) }
        )
    }

    fun loadColorSelectListRequest() {
        asyncOnIo(
            { colorDomain.getColorSelectList(preSelectedColor) },
            {
                selectedColorSelect = it.find { it.selected }

                mutableColorSelectListValue.postValue(it)
                mutablePreSelectedColorSelect.postValue(selectedColorSelect)
            }
        )
    }

    fun onTagColorSelectClicked(colorSelect: ColorSelectModel) {
        if (colorSelect != selectedColorSelect) {
            selectedColorSelect?.let { unSelectColorSelect(it) }
            selectColorSelect(colorSelect)

            selectedColorSelect = colorSelect
        }
    }

}