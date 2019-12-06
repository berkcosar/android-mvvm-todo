package info.tuver.todo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import info.tuver.todo.data.source.local.model.TagLocalModel

@Dao
interface TagDao {

    @Query("select * from tag")
    suspend fun selectList(): List<TagLocalModel>

    @Query("select * from tag where id = :id limit 1")
    suspend fun selectById(id: Long): TagLocalModel

    @Query("update tag set name = :name, color = :color where id = :id")
    suspend fun update(id: Long, name: String, color: String)

    @Insert
    suspend fun insert(tag: TagLocalModel): Long

    @Query("delete from tag where id = :id")
    suspend fun delete(id: Long)

}