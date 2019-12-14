package info.tuver.todo.domain

import info.tuver.todo.model.ColorSelectModel
import info.tuver.todo.data.repository.ColorRepository

class ColorDomainImpl(private val colorRepository: ColorRepository) : ColorDomain {

    override suspend fun getColorSelectList(): List<ColorSelectModel> {
        return colorRepository.getColorList().map { ColorSelectModel(it, false) }
    }

}