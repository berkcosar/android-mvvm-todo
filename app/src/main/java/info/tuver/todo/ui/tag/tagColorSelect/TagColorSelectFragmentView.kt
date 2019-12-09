package info.tuver.todo.ui.tag.tagColorSelect

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.data.model.ColorModel
import info.tuver.todo.data.model.ColorSelectModel
import info.tuver.todo.databinding.FragmentTagColorSelectBinding
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.common.SpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_tag_color_select.*
import org.koin.android.viewmodel.ext.android.getViewModel

class TagColorSelectFragmentView : BaseFragmentView<TagColorSelectFragmentViewModel, FragmentTagColorSelectBinding>(R.layout.fragment_tag_color_select), TagColorSelectAdapterActions {

    private val tagColorSelectAdapter = TagColorSelectAdapter(this)

    val selectedColor = ObservableField<ColorModel>()

    private fun updateColorSelectList(colorSelectModelList: List<ColorSelectModel>) {
        tagColorSelectAdapter.updateItemList(colorSelectModelList)
    }

    override fun createViewModel(): TagColorSelectFragmentViewModel {
        return getViewModel()
    }

    override fun setupView(context: Context) {
        fragment_tag_color_select_color_recycler.adapter = tagColorSelectAdapter
        fragment_tag_color_select_color_recycler.addItemDecoration(SpacingItemDecoration(context))

        viewModel.colorSelectListValue.observe(viewLifecycleOwner, Observer { updateColorSelectList(it) })
        viewModel.selectedColorSelectValue.observe(viewLifecycleOwner, Observer { selectedColor.set(it.color) })
    }

    override fun startView(context: Context) {
        viewModel.loadColorSelectListRequest()
    }

    override fun onTagColorSelectClicked(colorSelect: ColorSelectModel) {
        viewModel.onTagColorSelectClicked(colorSelect)
    }

}
