package na.severinchik.iba_kotlin_lesson8_room.database.entity

import androidx.room.*
import na.severinchik.iba_kotlin_lesson8_room.utils.DateTypeConverter
import java.util.*

@TypeConverters(DateTypeConverter::class)
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var uid: Long,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "datetime")
    var dateTime: Date,
    @ColumnInfo(name = "categoryId")
    var categoryId: Long

) {
    @Ignore
    constructor(name: String, content: String, dateTime: Date, categoryId: Long) : this(
        0,
        name,
        content,
        dateTime,
        categoryId
    )

}