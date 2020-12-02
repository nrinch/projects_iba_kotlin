package na.severinchik.iba_kotlin_lesson9

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}