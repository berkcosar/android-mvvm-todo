package info.tuver.todo.ui.todo.todoTagCreateDialog

import info.tuver.todo.ui.base.BaseDialogFragmentView
import info.tuver.todo.ui.tag.tagCreate.TagCreateEvents
import info.tuver.todo.ui.tag.tagCreate.TagCreateFragmentView
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoTagCreateDialogFragmentView : BaseDialogFragmentView<TodoTagCreateDialogFragmentViewModel, TagCreateFragmentView>(true) {

    override fun createViewModel(): TodoTagCreateDialogFragmentViewModel {
        return getViewModel()
    }

    override fun createFragment(): TagCreateFragmentView {
        return TagCreateFragmentView()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTagCreatedEvent(tagCreatedEvent: TagCreateEvents.TagCreatedEvent) {
        viewModel.onTagCreatedEvent(tagCreatedEvent.tag)
    }

}