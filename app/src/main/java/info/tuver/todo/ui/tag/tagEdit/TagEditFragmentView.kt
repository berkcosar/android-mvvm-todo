package info.tuver.todo.ui.tag.tagEdit

import android.content.Context
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.data.model.TagModel
import info.tuver.todo.databinding.FragmentTagEditBinding
import info.tuver.todo.extension.putSerializableArgument
import info.tuver.todo.extension.requireSerializableArgument
import info.tuver.todo.ui.base.BaseFragmentView
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class TagEditFragmentView : BaseFragmentView<TagEditFragmentViewModel, FragmentTagEditBinding>(R.layout.fragment_tag_edit) {

    private val tag: TagModel
        get() = requireSerializableArgument(KEY_TAG)

    override fun createViewModel(): TagEditFragmentViewModel {
        return getViewModel { parametersOf(tag) }
    }

    override fun setupView(context: Context) {
        viewModel.tagUpdatedEvent.observe(viewLifecycleOwner, Observer { publishEvent(TagEditEvents.TagEditedEvent(it)) })
        viewModel.tagDeletedEvent.observe(viewLifecycleOwner, Observer { publishEvent(TagEditEvents.TagDeletedEvent(it)) })
    }

    override fun startView(context: Context) {

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