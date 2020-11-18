package na.severinchik.iba_kotlin_lesson5_second_project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import na.severinchik.iba_kotlin_lesson5_second_project.R

class GlobalFragment : Fragment() {

    val someValue = 42
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_global, container, false)

        return root
    }
}