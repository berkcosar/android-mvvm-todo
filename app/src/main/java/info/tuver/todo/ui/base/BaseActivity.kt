package info.tuver.todo.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import info.tuver.todo.BR

abstract class BaseActivity<TViewModel : BaseActivityViewModel, TDataBinding : ViewDataBinding>(private val layoutResourceId: Int) : AppCompatActivity() {

    protected lateinit var viewModel: TViewModel

    protected abstract fun initView()

    protected abstract fun restoreView(savedInstanceState: Bundle)

    protected abstract fun setupView()

    protected abstract fun createViewModel(): TViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<TDataBinding>(this, layoutResourceId)

        viewModel = createViewModel()
        binding.setVariable(BR.viewModel, viewModel)

        savedInstanceState?.let { this.restoreView(it) } ?: this.initView()
        setupView()
    }

}