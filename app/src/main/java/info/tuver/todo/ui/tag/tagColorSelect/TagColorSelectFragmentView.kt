package info.tuver.todo.ui.tag.tagColorSelect

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.databinding.FragmentTagColorSelectBinding
import info.tuver.todo.extension.putSerializableArgument
import info.tuver.todo.model.ColorModel
import info.tuver.todo.model.ColorSelectModel
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.common.SpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_tag_color_select.*
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TagColorSelectFragmentView : BaseFragmentView<TagColorSelectFragmentViewModel, FragmentTagColorSelectBinding>(R.layout.fragment_tag_color_select), TagColorSelectAdapterActions {

    private val tagColorSelectAdapter = TagColorSelectAdapter(this)

    val selectedColor = ObservableField<ColorModel>()

    private val preSelectedColor: String?
        get() = arguments?.getString(KEY_PRE_SELECTED_COLOR)

    private fun updateColorSelectList(colorSelectModelList: List<ColorSelectModel>) {
        tagColorSelectAdapter.updateItemList(colorSelectModelList)
    }

    private fun updateColorSelected(colorSelect: ColorSelectModel) {
        tagColorSelectAdapter.notifyItemChanged(colorSelect)
        selectedColor.set(colorSelect.color)
    }

    private fun updateColorUnSelected(colorSelect: ColorSelectModel) {
        tagColorSelectAdapter.notifyItemChanged(colorSelect)
    }

    private fun scrollToColorSelect(colorSelectModel: ColorSelectModel?) {
        colorSelectModel?.let { tagColorSelectAdapter.scrollToItem(it) }
    }

    override fun createViewModel(): TagColorSelectFragmentViewModel {
        return getViewModel { parametersOf(preSelectedColor) }
    }

    override fun onSetupView(context: Context) {
        fragment_tag_color_select_color_recycler.adapter = tagColorSelectAdapter
        fragment_tag_color_select_color_recycler.addItemDecoration(SpacingItemDecoration(context))

        viewModel.colorSelectListValue.observe(viewLifecycleOwner, Observer { updateColorSelectList(it) })
        viewModel.colorSelectSelectedEvent.observe(viewLifecycleOwner, Observer { updateColorSelected(it) })
        viewModel.colorSelectUnSelectedEvent.observe(viewLifecycleOwner, Observer { updateColorUnSelected(it) })
        viewModel.preSelectedColorSelect.observe(viewLifecycleOwner, Observer { scrollToColorSelect(it) })
    }

    override fun onStartView(context: Context) {
        viewModel.loadColorSelectListRequest()
    }

    override fun onTagColorSelectClicked(colorSelect: ColorSelectModel) {
        viewModel.onTagColorSelectClicked(colorSelect)
    }

    companion object {

        private const val KEY_PRE_SELECTED_COLOR = "preSelectedColor"

        fun newInstance(preSelectedColor: String): TagColorSelectFragmentView {
            return TagColorSelectFragmentView().apply {
                putSerializableArgument(KEY_PRE_SELECTED_COLOR, preSelectedColor)
            }
        }

    }

}
