package info.tuver.todo.ui.todo.todoTagEditDialog

import info.tuver.todo.data.model.TagModel
import info.tuver.todo.extension.putSerializableArgument
import info.tuver.todo.extension.requireSerializableArgument
import info.tuver.todo.ui.base.BaseDialogFragmentView
import info.tuver.todo.ui.tag.tagEdit.TagEditEvents
import info.tuver.todo.ui.tag.tagEdit.TagEditFragmentView
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoTagEditDialogFragmentView : BaseDialogFragmentView<TodoTagEditDialogFragmentViewModel, TagEditFragmentView>(true) {

    private val tag: TagModel
        get() = requireSerializableArgument(KEY_TAG)

    override fun createViewModel(): TodoTagEditDialogFragmentViewModel {
        return getViewModel()
    }

    override fun createFragment(): TagEditFragmentView {
        return TagEditFragmentView.newInstance(tag)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTagEdited(tagEditedEvent: TagEditEvents.TagEditedEvent) {
        viewModel.onTagEdited(tagEditedEvent.tag)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTagDeleted(tagDeletedEvent: TagEditEvents.TagDeletedEvent) {
        viewModel.onTagDeleted(tagDeletedEvent.tag)
    }

    companion object {

        private const val KEY_TAG = "tag"

        fun newInstance(tag: TagModel): TodoTagEditDialogFragmentView {
            return TodoTagEditDialogFragmentView().apply {
                putSerializableArgument(KEY_TAG, tag)
            }
        }

    }

}