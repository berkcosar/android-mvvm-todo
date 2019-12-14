package info.tuver.todo.extension

import android.widget.EditText

fun EditText.focusAndShowKeyboard() {
    requestFocus()
    showKeyboard()
}
