package info.tuver.todo.ui.tag.tagEdit

import android.content.Context
import info.tuver.todo.R
import info.tuver.todo.databinding.FragmentTagEditBinding
import info.tuver.todo.extension.addFragment
import info.tuver.todo.extension.addOnPropertyChangedCallback
import info.tuver.todo.extension.putSerializableArgument
import info.tuver.todo.extension.requireSerializableArgument
import info.tuver.todo.model.TagModel
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.tag.tagColorSelect.TagColorSelectFragmentView
import kotlinx.android.synthetic.main.fragment_tag_edit.*
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TagEditFragmentView : BaseFragmentView<TagEditFragmentViewModel, FragmentTagEditBinding>(R.layout.fragment_tag_edit) {

    private val tag: TagModel
        get() = requireSerializableArgument(KEY_TAG)

    override fun createViewModel(): TagEditFragmentViewModel {
        return getViewModel { parametersOf(tag) }
    }

    override fun onSetupView(context: Context) {
        val tagColorSelectFragmentView = TagColorSelectFragmentView.newInstance(tag.color)

        addFragment(fragment_tag_edit_color_select_fragment_layout, tagColorSelectFragmentView)
        tagColorSelectFragmentView.selectedColor.addOnPropertyChangedCallback { viewModel.onTagColorSelected(it) }
    }

    override fun onStartView(context: Context) {

    }

    companion object {

        private const val KEY_TAG = "tag"

        fun newInstance(tag: TagModel): TagEditFragmentView {
            return TagEditFragmentView().apply {
                putSerializableArgument(KEY_TAG, tag)
            }
        }

    }

}