package info.tuver.todo.ui.tag.tagCreate

import android.content.Context
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.databinding.FragmentTagCreateBinding
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.tag.tagColorSelect.TagColorSelectEvents
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.viewmodel.ext.android.getViewModel

class TagCreateFragmentView : BaseFragmentView<TagCreateFragmentViewModel, FragmentTagCreateBinding>(R.layout.fragment_tag_create, true) {

    override fun createViewModel(): TagCreateFragmentViewModel {
        return getViewModel()
    }

    override fun setupView(context: Context) {
        viewModel.newTagCreatedEvent.observe(viewLifecycleOwner, Observer { publishEvent(TagCreateEvents.TagCreatedEvent(it)) })
    }

    override fun startView(context: Context) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTagColorSelectedEvent(tagColorSelectedEvent: TagColorSelectEvents.TagColorSelectedEvent) {
        viewModel.onTagColorSelectedEvent(tagColorSelectedEvent.color)
    }

}