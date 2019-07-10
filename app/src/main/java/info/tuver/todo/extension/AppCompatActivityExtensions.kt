package info.tuver.todo.extension

import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(containerFrameLayout: FrameLayout, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerFrameLayout.id, fragment)
        .commit()
}

fun AppCompatActivity.addFragment(containerFrameLayout: FrameLayout, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(containerFrameLayout.id, fragment)
        .addToBackStack(fragment.toString())
        .commit()
}