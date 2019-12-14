package info.tuver.todo.data.source.local.repository

import info.tuver.todo.data.repository.TagRepository
import info.tuver.todo.data.source.local.dao.TagDao
import info.tuver.todo.data.source.local.dao.TodoTagDao
import info.tuver.todo.data.source.local.model.TagLocalModel
import info.tuver.todo.model.TagModel

class TagRepositoryLocalImpl(private val tagDao: TagDao, private val todoTagDao: TodoTagDao) : TagRepository {

    override suspend fun getTagList(): List<TagModel> {
        return tagDao.selectList().map { it.toModel() }
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
        todoTagDao.deleteAll(tagId)
        tagDao.delete(tagId)
    }

}