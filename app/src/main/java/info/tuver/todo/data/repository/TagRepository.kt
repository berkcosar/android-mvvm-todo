package info.tuver.todo.data.repository

import info.tuver.todo.model.TagModel

interface TagRepository {

    suspend fun getTagList(): List<TagModel>

    suspend fun createTag(name: String, color: String): TagModel

    suspend fun updateTag(tagId: Long, name: String, color: String)

    suspend fun deleteTag(tagId: Long)

}