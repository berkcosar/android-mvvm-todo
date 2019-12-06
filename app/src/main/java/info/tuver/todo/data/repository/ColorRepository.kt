package info.tuver.todo.data.repository

import info.tuver.todo.data.model.ColorModel

interface ColorRepository {

    suspend fun getColorList(): List<ColorModel>

}