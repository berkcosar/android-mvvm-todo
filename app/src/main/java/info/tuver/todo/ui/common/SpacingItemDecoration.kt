package info.tuver.todo.ui.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import info.tuver.todo.R

class SpacingItemDecoration(context: Context, @DimenRes spacingDimenRes: Int = R.dimen.content_spacing_half) : RecyclerView.ItemDecoration() {

    private val spacingDimen = context.resources.getDimensionPixelSize(spacingDimenRes)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        parent.layoutManager?.let { layoutManager ->
            val adapterPosition = parent.getChildAdapterPosition(view)

            when (layoutManager) {
                is GridLayoutManager -> applyGridSpacing(layoutManager, adapterPosition, outRect)
                is LinearLayoutManager -> applyLinearSpacing(layoutManager, adapterPosition, outRect)
            }
        }
    }

    private fun applyGridSpacing(gridLayoutManager: GridLayoutManager, adapterPosition: Int, outRect: Rect) {
        val spanCount = gridLayoutManager.spanCount
        val column = adapterPosition % spanCount

        outRect.apply {
            left = column * spacingDimen / spanCount
            right = spacingDimen - (column + 1) * spacingDimen / spanCount

            if (adapterPosition >= spanCount) {
                top = spacingDimen
            }
        }
    }

    private fun applyLinearSpacing(linearLayoutManager: LinearLayoutManager, adapterPosition: Int, outRect: Rect) {
        if (adapterPosition > 0) {
            when (linearLayoutManager.orientation) {
                HORIZONTAL -> outRect.left = spacingDimen
                VERTICAL -> outRect.top = spacingDimen
            }
        }
    }

}