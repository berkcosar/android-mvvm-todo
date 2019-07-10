package info.tuver.todo.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.focusAndShowKeyboard(context: Context) {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

    requestFocus()
    inputMethodManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}
