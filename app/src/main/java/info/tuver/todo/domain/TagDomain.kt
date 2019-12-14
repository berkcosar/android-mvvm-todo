package info.tuver.todo.domain

import info.tuver.todo.model.TagModel
import info.tuver.todo.model.TagSelectModel
import io.reactivex.subjects.Subject

interface TagDomain {

    val tagCreatedSubject: Subject<TagModel>

    val tagUpdatedSubject: Subject<TagModel>

    val tagDeletedSubject: Subject<TagModel>

    suspend fun getTagSelectList(): List<TagSelectModel>

    suspend fun createTag(name: String, color: String): TagModel

    suspend fun updateTag(tag: TagModel, name: String, color: String)

    suspend fun deleteTag(tag: TagModel)

}