package na.severinchik.iba_kotlin_lesson7

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class SPFragment : Fragment() {
    val key = "key"
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        writeTOSP()
        val view = inflater.inflate(R.layout.fragment_s_p, container, false)
        view.findViewById<TextView>(R.id.sp_textView).text = readFromSP().toString()
        return view  // Inflate the layout for this fragment
    }

    fun writeTOSP() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putInt(key, 42)
            apply()
        }
    }

    fun readFromSP(): Int {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return 0

        return sharedPref.getInt(key, 0)
    }

}