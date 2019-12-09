package info.tuver.todo.ui.todo.todoTagSelect

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.data.model.TagModel
import info.tuver.todo.data.model.TagSelectModel
import info.tuver.todo.databinding.FragmentTodoTagSelectBinding
import info.tuver.todo.extension.showDialogFragment
import info.tuver.todo.ui.base.BaseFragmentView
import info.tuver.todo.ui.common.SpacingItemDecoration
import info.tuver.todo.ui.tag.tagCreate.TagCreateEvents
import info.tuver.todo.ui.tag.tagEdit.TagEditEvents
import info.tuver.todo.ui.todo.todoTagCreateDialog.TodoTagCreateDialogFragmentView
import info.tuver.todo.ui.todo.todoTagEditDialog.TodoTagEditDialogFragmentView
import kotlinx.android.synthetic.main.fragment_todo_tag_select.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.android.viewmodel.ext.android.getViewModel

class TodoTagSelectFragmentView : BaseFragmentView<TodoTagSelectFragmentViewModel, FragmentTodoTagSelectBinding>(R.layout.fragment_todo_tag_select, true), TodoTagSelectAdapterActions {

    private val todoTagSelectAdapter = TodoTagSelectAdapter(this)

    val selectedTagList = ObservableField<List<TagModel>>()

    private fun updateTagSelectList(tagSelectList: List<TagSelectModel>) {
        todoTagSelectAdapter.updateItemList(tagSelectList)
    }

    private fun showTagCreateDialog() {
        showDialogFragment(TodoTagCreateDialogFragmentView())
    }

    private fun showTagEditDialog(tag: TagModel) {
        showDialogFragment(TodoTagEditDialogFragmentView.newInstance(tag))
    }

    override fun createViewModel(): TodoTagSelectFragmentViewModel {
        return getViewModel()
    }

    override fun setupView(context: Context) {
        fragment_todo_tag_select_recycler.adapter = todoTagSelectAdapter
        fragment_todo_tag_select_recycler.addItemDecoration(SpacingItemDecoration(context))

        viewModel.selectedTagListValue.observe(viewLifecycleOwner, Observer { selectedTagList.set(it) })
        viewModel.tagSelectListValue.observe(viewLifecycleOwner, Observer { updateTagSelectList(it) })
        viewModel.showTagCreateViewEvent.observe(viewLifecycleOwner, Observer { showTagCreateDialog() })
        viewModel.showTagEditViewEvent.observe(viewLifecycleOwner, Observer { showTagEditDialog(it) })
    }

    override fun startView(context: Context) {
        viewModel.onLoadTagSelectListRequest()
    }

    override fun onTodoTagSelectClicked(tagSelect: TagSelectModel) {
        viewModel.onTagSelectClicked(tagSelect)
    }

    override fun onTodoTagSelectLongClicked(tagSelect: TagSelectModel) {
        viewModel.onTagSelectLongClicked(tagSelect)
    }

    override fun onCreateNewTagButtonClicked() {
        viewModel.onCreateNewTagButtonClicked()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTagCreated(tagCreatedEvent: TagCreateEvents.TagCreatedEvent) {
        viewModel.onTagCreated(tagCreatedEvent.tag)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTagEdited(tagEditedEvent: TagEditEvents.TagEditedEvent) {
        viewModel.onTagEdited(tagEditedEvent.tag)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTagDeleted(tagDeletedEvent: TagEditEvents.TagDeletedEvent) {
        viewModel.onTagDeleted(tagDeletedEvent.tag)
    }

}