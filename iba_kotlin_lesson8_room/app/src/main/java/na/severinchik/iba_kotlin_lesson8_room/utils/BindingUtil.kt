package na.severinchik.iba_kotlin_lesson8_room.utils

import android.widget.Button
import androidx.databinding.BindingAdapter
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Category

@BindingAdapter("firstChar")
fun Button.setFirstCharCategories(item:Category){
    text = item.name[0].toString()
}