package info.tuver.todo.ui.todo.todoTagEditDialog

import info.tuver.todo.model.TagModel
import info.tuver.todo.extension.putSerializableArgument
import info.tuver.todo.extension.requireSerializableArgument
import info.tuver.todo.ui.base.BaseDialogFragmentView
import info.tuver.todo.ui.tag.tagEdit.TagEditFragmentView
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoTagEditDialogFragmentView : BaseDialogFragmentView<TodoTagEditDialogFragmentViewModel, TagEditFragmentView>() {

    private val tag: TagModel
        get() = requireSerializableArgument(KEY_TAG)

    override fun createViewModel(): TodoTagEditDialogFragmentViewModel {
        return getViewModel()
    }

    override fun createFragment(): TagEditFragmentView {
        return TagEditFragmentView.newInstance(tag)
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