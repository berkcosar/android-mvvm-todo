package info.tuver.todo.extension

import android.view.View
import android.view.ViewGroup

fun <T: View> ViewGroup.addViewList(viewList: List<T>) {
    viewList.forEach { addView(it) }
}