package info.tuver.todo.domain

import info.tuver.todo.model.ColorSelectModel
import io.reactivex.subjects.Subject

interface ColorDomain {

    val colorSelectUpdatedSubject: Subject<ColorSelectModel>

    suspend fun getColorSelectList(): List<ColorSelectModel>

    suspend fun getColorSelectList(preSelectedColor: String?): List<ColorSelectModel>

    suspend fun updateColorSelectSelected(colorSelect: ColorSelectModel, selected: Boolean)

}