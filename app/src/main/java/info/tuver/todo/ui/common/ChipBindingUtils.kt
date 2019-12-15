package info.tuver.todo.ui.common

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip

@BindingAdapter("chipBackgroundColor")
fun setChipBackgroundColorAttribute(chip: Chip, backgroundColor: String) {
    chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor(backgroundColor))
}