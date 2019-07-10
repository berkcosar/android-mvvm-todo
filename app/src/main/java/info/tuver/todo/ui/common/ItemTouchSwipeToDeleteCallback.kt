package info.tuver.todo.ui.common

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.RecyclerView

class ItemTouchSwipeToDeleteCallback(private val itemTouchSwipeToDeleteCallbackListener: ItemTouchSwipeToDeleteCallbackListener) : ItemTouchHelper.SimpleCallback(0, LEFT) {

    interface ItemTouchSwipeToDeleteCallbackListener {

        fun onItemSwipedToDelete(position: Int)

    }

    interface ItemTouchSwipeToDeleteViewHolder {

        val containerView: View

    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView((viewHolder as ItemTouchSwipeToDeleteViewHolder).containerView)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected((viewHolder as ItemTouchSwipeToDeleteViewHolder).containerView)
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, (viewHolder as ItemTouchSwipeToDeleteViewHolder).containerView, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        ItemTouchHelper.Callback.getDefaultUIUtil().onDrawOver(c, recyclerView, (viewHolder as ItemTouchSwipeToDeleteViewHolder).containerView, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemTouchSwipeToDeleteCallbackListener.onItemSwipedToDelete(viewHolder.adapterPosition)
    }

}