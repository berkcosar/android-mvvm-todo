package info.tuver.todo.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import info.tuver.todo.R
import info.tuver.todo.extension.replaceFragment
import kotlinx.android.synthetic.main.fragment_base_dialog.view.*

abstract class BaseDialogFragmentView<TViewModel : BaseDialogFragmentViewModel, TFragment : Fragment> : DialogFragment() {

    protected lateinit var viewModel: TViewModel

    protected abstract fun createViewModel(): TViewModel

    protected abstract fun createFragment(): TFragment

    @CallSuper
    protected open fun setupView(context: Context) {
        viewModel.completedEvent.observe(viewLifecycleOwner, Observer { dismiss() })
    }

    protected open fun onStartView(context: Context) {

    }

    protected open fun onRestoreView(context: Context, savedInstanceState: Bundle) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = createViewModel()

        return inflater.inflate(R.layout.fragment_base_dialog, container).apply {
            replaceFragment(fragment_base_dialog_frame_layout, createFragment())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { safeContext ->
            setupView(safeContext)
            savedInstanceState?.let { onRestoreView(safeContext, it) } ?: onStartView(safeContext)
        }
    }

}