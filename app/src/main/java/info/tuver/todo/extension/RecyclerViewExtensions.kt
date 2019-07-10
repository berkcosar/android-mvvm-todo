package info.tuver.todo.extension

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addItemTouchHelper(callback: ItemTouchHelper.Callback) {
    ItemTouchHelper(callback).attachToRecyclerView(this)
}
