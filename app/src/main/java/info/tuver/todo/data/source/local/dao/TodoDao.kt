package info.tuver.todo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import info.tuver.todo.data.model.TodoModel

@Dao
interface TodoDao {

    @Query("select * from todo order by createdDate asc")
    suspend fun selectList(): List<TodoModel>

    @Query("select * from todo where id = :id limit 1")
    suspend fun selectById(id: Long): TodoModel

    @Insert
    suspend fun insert(todo: TodoModel): Long

    @Query("delete from todo where id = :id")
    suspend fun delete(id: Long)

    @Query("update todo set completed = :completed where id = :id")
    suspend fun updateCompleted(id: Long, completed: Boolean)

}