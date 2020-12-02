package na.severinchik.iba_kotlin_lesson8_room.viewmodel.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import na.severinchik.iba_kotlin_lesson8_room.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class MainViewModelFactory (   private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel( application) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }

}