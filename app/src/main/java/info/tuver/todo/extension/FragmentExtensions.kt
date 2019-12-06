package info.tuver.todo.extension

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import info.tuver.todo.ui.base.BaseActivity
import java.io.Serializable

inline fun <reified T> Fragment.inflate(@LayoutRes layoutResourceId: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): T {
    return layoutInflater.inflate(layoutResourceId, root, attachToRoot) as T
}

val Fragment.baseActivity: BaseActivity?
    get() = activity as? BaseActivity

fun Fragment.replaceFragment(containerFrameLayout: FrameLayout, fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .replace(containerFrameLayout.id, fragment)
        .commit()
}

fun Fragment.addFragment(containerFrameLayout: FrameLayout, fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .add(containerFrameLayout.id, fragment)
        .addToBackStack(fragment.toString())
        .commit()
}

fun Fragment.showDialogFragment(dialogFragment: DialogFragment) {
    dialogFragment.show(childFragmentManager, null)
}

fun Fragment.showSnackbar(@StringRes messageResourceId: Int, @StringRes actionResourceId: Int? = null, actionCallback: () -> Unit = { }) {
    baseActivity?.coordinatorLayout?.let {
        Snackbar.make(it, messageResourceId, Snackbar.LENGTH_SHORT).apply {
            if (actionResourceId != null) {
                setAction(actionResourceId, { actionCallback() })
            }

            show()
        }
    }
}

fun Fragment.putSerializableArgument(key: String, value: Serializable) {
    arguments = (arguments ?: Bundle()).apply {
        putSerializable(key, value)
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> Fragment.requireSerializableArgument(key: String): T {
    return arguments!!.getSerializable(key) as T
}