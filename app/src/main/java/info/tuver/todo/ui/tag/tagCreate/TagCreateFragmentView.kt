package info.tuver.todo.ui.tag.tagCreate

import android.content.Context
import info.tuver.todo.R
import info.tuver.todo.databinding.FragmentTagCreateBinding
import info.tuver.todo.extension.addOnPropertyChangedCallback
import info.tuver.todo.extension.getFragment
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.tag.tagColorSelect.TagColorSelectFragmentView
import org.koin.android.viewmodel.ext.android.getViewModel

class TagCreateFragmentView : BaseFragmentView<TagCreateFragmentViewModel, FragmentTagCreateBinding>(R.layout.fragment_tag_create) {

    private val tagColorSelectFragmentView: TagColorSelectFragmentView
        get() = getFragment(R.id.fragment_tag_create_color_select_fragment)

    override fun createViewModel(): TagCreateFragmentViewModel {
        return getViewModel()
    }

    override fun onSetupView(context: Context) {
        tagColorSelectFragmentView.selectedColor.addOnPropertyChangedCallback { viewModel.onTagColorSelected(it) }
    }

    override fun onStartView(context: Context) {

    }

}