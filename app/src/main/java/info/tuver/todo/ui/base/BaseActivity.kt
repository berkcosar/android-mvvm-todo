package info.tuver.todo.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout

abstract class BaseActivity : AppCompatActivity() {

    abstract val coordinatorLayout: CoordinatorLayout
        get

}