package info.tuver.todo.ui.common

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.annotation.DimenRes
import com.google.android.flexbox.FlexboxItemDecoration
import info.tuver.todo.R

class FlexSpacingItemDecoration(context: Context, @DimenRes spacingDimenRes: Int = R.dimen.content_spacing_half) : FlexboxItemDecoration(context) {

    init {
        val spacingDimen = context.resources.getDimensionPixelSize(spacingDimenRes)
        val drawable = GradientDrawable().apply {
            setSize(spacingDimen, spacingDimen)
        }

        setDrawable(drawable)
        setOrientation(BOTH)
    }

}