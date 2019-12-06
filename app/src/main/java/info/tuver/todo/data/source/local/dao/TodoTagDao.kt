package info.tuver.todo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import info.tuver.todo.data.source.local.model.TodoTagLocalModel

@Dao
interface TodoTagDao {

    @Insert
    suspend fun insert(todoTag: TodoTagLocalModel)

    @Insert
    suspend fun insertList(todoTagList: List<TodoTagLocalModel>)

    @Query("delete from todo_tag where todoId = :todoId and tagId = :tagId")
    suspend fun delete(todoId: Long, tagId: Long)

    @Query("delete from todo_tag where todoId = :todoId")
    suspend fun deleteAll(todoId: Long)

}