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

abstract class BaseFragment<TViewModel : BaseFragmentViewModel, TDataBinding : ViewDataBinding>(private val layoutResourceId: Int, private val consumesEvents: Boolean = false) : Fragment() {

    protected lateinit var viewModel: TViewModel

    protected abstract fun createViewModel(): TViewModel

    protected abstract fun initView(context: Context)

    open protected fun restoreView(context: Context, savedInstanceState: Bundle) {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let { safeContext ->
            initView(safeContext)
            savedInstanceState?.let { restoreView(safeContext, it) }
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