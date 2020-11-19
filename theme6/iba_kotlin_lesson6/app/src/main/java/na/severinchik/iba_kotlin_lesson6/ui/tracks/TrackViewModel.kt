package na.severinchik.iba_kotlin_lesson6.ui.tracks

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class TrackViewModel : ViewModel() {

    var text: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = "This is track Fragment"
    }


}