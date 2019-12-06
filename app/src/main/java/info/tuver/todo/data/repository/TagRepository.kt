package info.tuver.todo.data.repository

import info.tuver.todo.data.model.TagModel

interface TagRepository {

    suspend fun getTagList(): List<TagModel>

    suspend fun createTag(name: String, color: String): TagModel

    suspend fun deleteTag(tagId: Long)

    suspend fun updateTag(tagId: Long, name: String, color: String)

}