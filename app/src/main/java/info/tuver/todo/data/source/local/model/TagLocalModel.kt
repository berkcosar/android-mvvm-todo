package info.tuver.todo.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import info.tuver.todo.data.model.TagModel

@Entity(tableName = "tag")
class TagLocalModel(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "color") var color: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
) : BaseLocalModel<TagModel>() {

    override fun toModel(): TagModel {
        return TagModel(
            id,
            name,
            color
        )
    }

}