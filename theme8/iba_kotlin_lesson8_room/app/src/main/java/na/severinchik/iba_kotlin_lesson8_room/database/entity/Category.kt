package na.severinchik.iba_kotlin_lesson8_room.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var uid: Long,
    @ColumnInfo(name = "name")
    var name: String
) {
    @Ignore
    constructor(name: String) : this(0L, name)
}