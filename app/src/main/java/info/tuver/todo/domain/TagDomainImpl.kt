package info.tuver.todo.domain

import info.tuver.todo.data.repository.TagRepository
import info.tuver.todo.model.TagModel
import info.tuver.todo.model.TagSelectModel
import io.reactivex.subjects.PublishSubject

class TagDomainImpl(private val tagRepository: TagRepository) : TagDomain {

    override val tagCreatedSubject = PublishSubject.create<TagModel>()

    override val tagUpdatedSubject = PublishSubject.create<TagModel>()

    override val tagDeletedSubject = PublishSubject.create<TagModel>()

    override suspend fun getTagSelectList(): List<TagSelectModel> {
        return tagRepository.getTagList().map { TagSelectModel(it, false) }
    }

    override suspend fun createTag(name: String, color: String): TagModel {
        return tagRepository.createTag(name, color).also {
            tagCreatedSubject.onNext(it)
        }
    }

    override suspend fun updateTag(tag: TagModel, name: String, color: String) {
        tagRepository.updateTag(tag.id, name, color)

        tag.also {
            it.name = name
            it.color = color

            tagUpdatedSubject.onNext(it)
        }
    }

    override suspend fun deleteTag(tag: TagModel) {
        tagRepository.deleteTag(tag.id)
        tagDeletedSubject.onNext(tag)
    }
}