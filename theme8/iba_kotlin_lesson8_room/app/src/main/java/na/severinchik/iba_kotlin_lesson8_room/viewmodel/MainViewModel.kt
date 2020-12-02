package na.severinchik.iba_kotlin_lesson8_room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import na.severinchik.iba_kotlin_lesson8_room.database.AppDatabase
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Category
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Note
import na.severinchik.iba_kotlin_lesson8_room.repositories.CategoryRepository
import na.severinchik.iba_kotlin_lesson8_room.repositories.NoteRepository

class MainViewModel(application: Application) :
    AndroidViewModel(application) {
    var database = AppDatabase.getInstance(application)
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var categoryRepository: CategoryRepository
    private var noteRepository: NoteRepository


    var categories: LiveData<List<Category>>
    var notes: LiveData<List<Note>>

    init {
        categoryRepository = CategoryRepository(database)
        noteRepository = NoteRepository(database)
        categories = categoryRepository.categories
        notes = noteRepository.notes
    }


    fun insertCategory(category: Category) {
        uiScope.launch {
            categoryRepository.insert(category)
        }
    }

    fun insertNote(note: Note) {
        repeat(100) {
            uiScope.launch {
                delay(500L)
                noteRepository.insert(note)
            }
        }
    }

    fun deleteNote(note: Note) {
        uiScope.launch {
            noteRepository.delete(note)
        }
    }


    fun getItemTouchHelperCategoriesCallback(): ItemTouchHelper.SimpleCallback? {
        return object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                notes.value?.get(viewHolder.adapterPosition)?.let { deleteNote(it) }
            }
        }
    }
}