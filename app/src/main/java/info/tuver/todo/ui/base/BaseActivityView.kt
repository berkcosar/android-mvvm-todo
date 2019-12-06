package info.tuver.todo.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import info.tuver.todo.BR
import org.greenrobot.eventbus.EventBus

abstract class BaseActivityView<TViewModel : BaseActivityViewModel, TDataBinding : ViewDataBinding>(private val layoutResourceId: Int, private val consumesEvents: Boolean = false) : BaseActivity() {

    protected lateinit var viewModel: TViewModel

    protected abstract fun createViewModel(): TViewModel

    protected abstract fun setupView()

    protected abstract fun startView()

    protected open fun restoreView(savedInstanceState: Bundle) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<TDataBinding>(this, layoutResourceId)

        viewModel = createViewModel()
        binding.setVariable(BR.viewModel, viewModel)

        setupView()
        savedInstanceState?.let { this.restoreView(it) } ?: this.startView()
    }

    override fun onStart() {
        super.onStart()

        if (consumesEvents) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        if (consumesEvents) {
            EventBus.getDefault().unregister(this)
        }

        super.onStop()
    }

}