package info.tuver.todo.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.focusAndShowKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let {
        requestFocus()
        it.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}
