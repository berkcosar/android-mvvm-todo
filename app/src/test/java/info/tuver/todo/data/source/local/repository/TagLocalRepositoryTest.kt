package info.tuver.todo.data.source.local.repository

import com.nhaarman.mockitokotlin2.*
import info.tuver.todo.base.BaseTest
import info.tuver.todo.data.model.TagModel
import info.tuver.todo.data.source.local.dao.TagDao
import info.tuver.todo.extension.once
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TagLocalRepositoryTest : BaseTest() {

    private val tagId = 321L

    private val tagName = "test tag name"

    private val tagColor = 33324

    private val tag = TagModel(tagName, tagColor).apply { id = tagId }

    private val tagList = listOf(tag)

    private lateinit var tagDao: TagDao

    private lateinit var tagLocalRepository: TagLocalRepository

    override fun onSetup() {
        tagDao = mock()
        tagLocalRepository = TagLocalRepository(tagDao)
    }

    @Test
    fun `should call select list when get tag list`() = runBlocking {
        whenever(tagDao.selectList()).doReturn(tagList)

        val getTagListResult = tagLocalRepository.getTagList()

        verify(tagDao, once()).selectList()
        assertEquals(tagList, getTagListResult)
    }

    @Test
    fun `should call insert and select by id when create tag`() = runBlocking<Unit> {
        val tagCaptor = argumentCaptor<TagModel>()

        whenever(tagDao.insert(any())).doReturn(tagId)
        whenever(tagDao.selectById(tagId)).doReturn(tag)

        val createTagResult = tagLocalRepository.createTag(tagName, tagColor)

        verify(tagDao, once()).insert(tagCaptor.capture())
        verify(tagDao, once()).selectById(tagId)
        assertEquals(tagCaptor.lastValue.name, tagName)
        assertEquals(tagCaptor.lastValue.color, tagColor)
        assertEquals(createTagResult, tag)
    }

    @Test
    fun `should call delete when delete tag`() = runBlocking<Unit> {
        tagLocalRepository.deleteTag(tag)

        verify(tagDao, once()).delete(tagId)
    }

}