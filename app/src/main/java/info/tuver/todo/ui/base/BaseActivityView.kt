package info.tuver.todo.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import info.tuver.todo.BR

abstract class BaseActivityView<TViewModel : BaseActivityViewModel, TDataBinding : ViewDataBinding>(private val layoutResourceId: Int) : BaseActivity() {

    protected lateinit var viewModel: TViewModel

    private fun setupView() {
        viewModel.completedEvent.observe(this, Observer { finish() })
        onSetupView()
    }

    protected abstract fun createViewModel(): TViewModel

    protected abstract fun onSetupView()

    protected abstract fun onStartView()

    protected open fun onRestoreView(savedInstanceState: Bundle) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<TDataBinding>(this, layoutResourceId)

        viewModel = createViewModel()
        binding.setVariable(BR.viewModel, viewModel)

        setupView()
        savedInstanceState?.let { this.onRestoreView(it) } ?: this.onStartView()
    }

}