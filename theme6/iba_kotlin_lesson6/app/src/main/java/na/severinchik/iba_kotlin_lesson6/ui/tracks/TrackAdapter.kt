package na.severinchik.iba_kotlin_lesson6.ui.tracks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import na.severinchik.iba_kotlin_lesson6.R

class TrackAdapter(private val vales: List<String>, val context: Context) : BaseAdapter() {


    override fun getCount() = vales.size
    override fun getItem(p0: Int): Any {
        return vales[p0]
    }

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item, p2, false)
        view.findViewById<TextView>(R.id.text_view).text = vales[p0]
        return view;
    }

}