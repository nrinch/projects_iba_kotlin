package na.severinchik.iba_kotlin_lesson8_room.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import na.severinchik.iba_kotlin_lesson8_room.database.AppDatabase
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Note

class NoteRepository(private val database: AppDatabase) {
    private val noteDao = database.noteDao
    val notes: LiveData<List<Note>>
        get() = noteDao.getAll()

    suspend fun insert(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }

    suspend fun delete(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.delete(note.uid)
        }
    }
}