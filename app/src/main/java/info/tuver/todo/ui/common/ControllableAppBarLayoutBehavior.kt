package info.tuver.todo.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class ControllableAppBarLayoutBehavior @JvmOverloads constructor(var isEnabled: Boolean = true, context: Context? = null, attrs: AttributeSet? = null) : AppBarLayout.Behavior(context, attrs) {

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
        return isEnabled && super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type)
    }

}