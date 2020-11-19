package na.severinchik.iba_kotlin_lesson6.ui

import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.databinding.BindingAdapter

@BindingAdapter("somedata")
fun TextView.SomeData( value:Int){
    text = value.toString()
}

