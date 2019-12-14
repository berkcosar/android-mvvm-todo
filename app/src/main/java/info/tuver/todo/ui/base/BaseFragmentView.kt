package info.tuver.todo.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import info.tuver.todo.BR

abstract class BaseFragmentView<TViewModel : BaseFragmentViewModel, TDataBinding : ViewDataBinding>(private val layoutResourceId: Int) : Fragment() {

    protected lateinit var viewModel: TViewModel

    protected abstract fun createViewModel(): TViewModel

    protected abstract fun onSetupView(context: Context)

    protected abstract fun onStartView(context: Context)

    protected open fun onRestoreView(context: Context, savedInstanceState: Bundle) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<TDataBinding>(inflater, layoutResourceId, container, false)

        viewModel = createViewModel()
        binding.setVariable(BR.viewModel, viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { safeContext ->
            onSetupView(safeContext)
            savedInstanceState?.let { onRestoreView(safeContext, it) } ?: onStartView(safeContext)
        }
    }

}