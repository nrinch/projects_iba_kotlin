package na.severinchik.iba_kotlin_lesson6.ui.band

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BandViewModel : ViewModel() {
    var selectedBand = MutableLiveData<String>().apply { "Error!?" }
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
     val bands = MutableLiveData<List<Band>>().apply {
        value = bandSource()

    }
//    val bands: LiveData<List<Band>> = _bands
    fun addBand() {
//cuz no storage
        var temp = bands.value as ArrayList<Band>
        temp.add(
            Band(
                6,
                "Metallica",
                "Trash Metal",
                1981,
                "Los Angeles, California, U.S."
            )
        )

        bands.apply {
            value = temp
        }


    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ViewModel", "onCleared: ")
    }

}