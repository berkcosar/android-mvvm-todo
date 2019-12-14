package info.tuver.todo.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import androidx.coordinatorlayout.widget.CoordinatorLayout

private fun View.getInputMethodManager(): InputMethodManager? {
    return context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
}

fun View.showKeyboard() {
    getInputMethodManager()?.showSoftInput(this, SHOW_IMPLICIT)
}

fun <T : View> T.setCoordinatorLayoutBehavior(behavior: CoordinatorLayout.Behavior<T>) {
    (layoutParams as CoordinatorLayout.LayoutParams).behavior = behavior
}