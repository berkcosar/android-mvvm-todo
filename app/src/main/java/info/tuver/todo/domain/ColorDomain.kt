package info.tuver.todo.domain

import info.tuver.todo.model.ColorSelectModel

interface ColorDomain {

    suspend fun getColorSelectList(): List<ColorSelectModel>

}