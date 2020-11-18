package na.severinchik.iba_kotlin_lesson5_second_project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import na.severinchik.iba_kotlin_lesson5_second_project.R

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        var action = HomeFragmentDirections.actionNavigationHomeToDetailFragment()
        root.findViewById<ImageView>(R.id.first_image).setOnClickListener { view: View ->
            action.photo = R.drawable.bonfire
            action.about = "This is cool bonfire"
            view.findNavController().navigate(action)
        }
        root.findViewById<ImageView>(R.id.second_image).setOnClickListener { view: View ->
            action.photo = R.drawable.moon
            action.about = "Moon"
            view.findNavController().navigate(action)
        }
        root.findViewById<ImageView>(R.id.third_image).setOnClickListener { view: View ->
            action.photo = R.drawable.sunshine
            action.about = "Good Morning"
            view.findNavController().navigate(action)
        }
        return root
    }
}