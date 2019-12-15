package info.tuver.todo.domain

import info.tuver.todo.model.ColorSelectModel
import info.tuver.todo.data.repository.ColorRepository
import io.reactivex.subjects.PublishSubject

class ColorDomainImpl(private val colorRepository: ColorRepository) : ColorDomain {

    override val colorSelectUpdatedSubject = PublishSubject.create<ColorSelectModel>()

    override suspend fun getColorSelectList(): List<ColorSelectModel> {
        return colorRepository.getColorList().map { ColorSelectModel(it, false) }
    }

    override suspend fun getColorSelectList(preSelectedColor: String?): List<ColorSelectModel> {
        val colorSelectList = getColorSelectList()

        preSelectedColor?.let {
            val preSelectedColorSelect = colorSelectList.find { colorSelect ->
                colorSelect.color.color == it
            }

            preSelectedColorSelect?.selected = true
        }

        return colorSelectList
    }

    override suspend fun updateColorSelectSelected(colorSelect: ColorSelectModel, selected: Boolean) {
        colorSelect.selected = selected
        colorSelectUpdatedSubject.onNext(colorSelect)
    }

}