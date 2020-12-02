package na.severinchik.iba_kotlin_lesson8_room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import na.severinchik.iba_kotlin_lesson8_room.database.dao.CategoryDao
import na.severinchik.iba_kotlin_lesson8_room.database.dao.NoteDao
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Category
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Note

@Database(
    entities = [Category::class, Note::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val categoryDao: CategoryDao
    abstract val noteDao: NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}