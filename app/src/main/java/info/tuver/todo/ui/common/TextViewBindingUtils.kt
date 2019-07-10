package info.tuver.todo.ui.common

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("textStrikeThru")
fun setTextViewStrikeThruAttribute(textView: TextView, strikeThru: Boolean) {
    textView.paintFlags = when {
        strikeThru -> textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else -> textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}
