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
import org.greenrobot.eventbus.EventBus

abstract class BaseFragmentView<TViewModel : BaseFragmentViewModel, TDataBinding : ViewDataBinding>(private val layoutResourceId: Int, private val consumesEvents: Boolean = false) : Fragment() {

    protected lateinit var viewModel: TViewModel

    protected abstract fun createViewModel(): TViewModel

    protected abstract fun setupView(context: Context)

    protected abstract fun startView(context: Context)

    protected open fun restoreView(context: Context, savedInstanceState: Bundle) {

    }

    protected fun publishEvent(event: BaseEvent) {
        EventBus.getDefault().post(event)
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
            setupView(safeContext)
            savedInstanceState?.let { restoreView(safeContext, it) } ?: startView(safeContext)
        }
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