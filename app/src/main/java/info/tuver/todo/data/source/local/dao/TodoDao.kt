package info.tuver.todo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import info.tuver.todo.data.source.local.model.TagLocalModel
import info.tuver.todo.data.source.local.model.TodoLocalModel

@Dao
interface TodoDao {

    @Query("select * from todo where deleted = 0 order by createdDate asc")
    suspend fun selectList(): List<TodoLocalModel>

    @Query("select * from todo where id in (select todoId from todo_tag where tagId in (:tagIdList) group by todoId having count(*) = :tagIdListSize) and deleted = 0 order by createdDate asc")
    suspend fun selectList(tagIdList: List<Long>, tagIdListSize: Int): List<TodoLocalModel>

    @Query("select * from tag where id in (select tagId from todo_tag where todoId = :todoId)")
    suspend fun selectTagList(todoId: Long): List<TagLocalModel>

    @Query("select * from todo where id = :id limit 1")
    suspend fun selectById(id: Long): TodoLocalModel

    @Insert
    suspend fun insert(todo: TodoLocalModel): Long

    @Query("update todo set deleted = 1 where id = :id")
    suspend fun delete(id: Long)

    @Query("update todo set deleted = 0 where id = :id")
    suspend fun undoDelete(id: Long)

    @Query("update todo set completed = :completed where id = :id")
    suspend fun updateCompleted(id: Long, completed: Boolean)

}