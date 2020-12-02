package na.severinchik.iba_kotlin_lesson8_room.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Note

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Query("SELECT * from note_table WHERE uid=:uid")
    fun get(uid: Long): Note

    @Query("SELECT * from note_table")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * from note_table WHERE categoryId=:categoryId")
    fun getAllbyCategory(categoryId: Long): LiveData<List<Note>>

    @Query("DELETE from note_table WHERE uid=:uid")
    fun delete(uid: Long)
}