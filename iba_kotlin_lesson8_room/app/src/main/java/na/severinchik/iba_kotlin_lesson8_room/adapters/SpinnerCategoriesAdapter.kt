package na.severinchik.iba_kotlin_lesson8_room.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.TextView
import na.severinchik.iba_kotlin_lesson8_room.R
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Category

class SpinnerCategoriesAdapter(private val vales: List<Category>, val context: Context) : BaseAdapter() {

    override fun getCount() = vales.size
    override fun getItem(p0: Int): Any {
        return vales[p0]
    }

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.spinner_item, p2, false)
        view.findViewById<TextView>(R.id.item_spinner).text = vales[p0].name
        return view;
    }
}