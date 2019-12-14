package info.tuver.todo.ui.common

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun setViewVisibleAttribute(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

@BindingAdapter("backgroundColor")
fun setBackgroundColorAttribute(view: View, backgroundColor: String) {
    view.setBackgroundColor(Color.parseColor(backgroundColor))
}

@BindingAdapter("borderColor")
fun setBorderColorAttribute(view: View, backgroundColor: String) {
    view.background = GradientDrawable().apply {
        setStroke(1, Color.parseColor(backgroundColor));
    }
}

@BindingAdapter("textColor")
fun setTextColorAttribute(textView: TextView, backgroundColor: String) {
    textView.setTextColor(Color.parseColor(backgroundColor))
}
