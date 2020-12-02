package na.severinchik.iba_kotlin_lesson8_room.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import na.severinchik.iba_kotlin_lesson8_room.database.AppDatabase
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Category

class CategoryRepository(private val database: AppDatabase) {
    private val categoryDao = database.categoryDao

    val categories: LiveData<List<Category>>
        get() = categoryDao.getAll();

    suspend fun insert(category: Category) {
        withContext(Dispatchers.IO) {
            categoryDao.insert(category)
        }
    }

    suspend fun delete(category: Category) {
        withContext(Dispatchers.IO) {
            categoryDao.delete(category.uid)
        }
    }


}