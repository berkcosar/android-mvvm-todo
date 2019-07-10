package info.tuver.todo.ui.common

import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("fabVisible")
fun setFloatingActionButtonVisibleAttribute(floatingActionButton: FloatingActionButton, visible: Boolean) {
    when {
        visible -> floatingActionButton.show()
        else -> floatingActionButton.hide()
    }
}
