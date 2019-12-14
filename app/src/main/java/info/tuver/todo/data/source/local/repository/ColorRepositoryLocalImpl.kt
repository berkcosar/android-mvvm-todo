package info.tuver.todo.data.source.local.repository

import info.tuver.todo.model.ColorModel
import info.tuver.todo.data.repository.ColorRepository

class ColorRepositoryLocalImpl : ColorRepository {

    override suspend fun getColorList(): List<ColorModel> {
        return colorList.map { ColorModel((it)) }
    }

    companion object {

        private val colorList = listOf(
            "#fff44336", "#ffe91e63", "#ff9c27b0", "#ff673ab7",
            "#ff3f51b5", "#ff2196f3", "#ff03a9f4", "#ff00bcd4",
            "#ff009688", "#ff4caf50", "#ff8bc34a", "#ffcddc39",
            "#ffffeb3b", "#ffffc107", "#ffff9800", "#ffff5722",
            "#ff795548", "#ff9e9e9e", "#ff607d8b", "#ff333333"
        )

    }

}