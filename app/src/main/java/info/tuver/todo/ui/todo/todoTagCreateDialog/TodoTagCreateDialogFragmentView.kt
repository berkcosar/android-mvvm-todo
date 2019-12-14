package info.tuver.todo.ui.todo.todoTagCreateDialog

import info.tuver.todo.ui.base.BaseDialogFragmentView
import info.tuver.todo.ui.tag.tagCreate.TagCreateFragmentView
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoTagCreateDialogFragmentView : BaseDialogFragmentView<TodoTagCreateDialogFragmentViewModel, TagCreateFragmentView>() {

    override fun createViewModel(): TodoTagCreateDialogFragmentViewModel {
        return getViewModel()
    }

    override fun createFragment(): TagCreateFragmentView {
        return TagCreateFragmentView()
    }

}