package info.tuver.todo.ui.tag

import info.tuver.todo.model.TagModel
import info.tuver.todo.ui.tag.tagColorSelect.TagColorSelectFragmentViewModel
import info.tuver.todo.ui.tag.tagCreate.TagCreateFragmentViewModel
import info.tuver.todo.ui.tag.tagEdit.TagEditFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tagModule = module {
    viewModel { TagCreateFragmentViewModel(get(), get()) }
    viewModel { (tag: TagModel) -> TagEditFragmentViewModel(get(), get(), tag) }
    viewModel { TagColorSelectFragmentViewModel(get(), get()) }
}