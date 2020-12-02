package na.severinchik.iba_kotlin_lesson8_room.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Category

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Query("SELECT * from category_table WHERE uid=:uid")
    fun get(uid: Long): Category

    @Query("SELECT * from category_table")
    fun getAll(): LiveData<List<Category>>

    @Query("DELETE from category_table WHERE uid=:uid")
    fun delete(uid: Long)
    @Delete
    fun delete(category: Category)
}