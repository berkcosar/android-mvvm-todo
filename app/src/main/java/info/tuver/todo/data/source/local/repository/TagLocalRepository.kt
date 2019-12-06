package info.tuver.todo.data.source.local.repository

import info.tuver.todo.data.model.TagModel
import info.tuver.todo.data.repository.TagRepository
import info.tuver.todo.data.source.local.dao.TagDao
import info.tuver.todo.data.source.local.model.TagLocalModel

class TagLocalRepository(private val tagDao: TagDao) : TagRepository {

    override suspend fun getTagList(): List<TagModel> {
        return tagDao.selectList().map { localTag -> localTag.toModel() }
    }

    override suspend fun createTag(name: String, color: String): TagModel {
        val tag = TagLocalModel(name.trim(), color)
        val id = tagDao.insert(tag)

        return tagDao.selectById(id).toModel()
    }

    override suspend fun updateTag(tagId: Long, name: String, color: String) {
        tagDao.update(tagId, name, color)
    }

    override suspend fun deleteTag(tagId: Long) {
        tagDao.delete(tagId)
    }

}