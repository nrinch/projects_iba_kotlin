package na.severinchik.iba_kotlin_lesson5_second_project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import na.severinchik.iba_kotlin_lesson5_second_project.R

class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val args = DashboardFragmentArgs.fromBundle(requireArguments())

        args.let {
            root.findViewById<TextView>(R.id.text_dashboard).text = it.count.toString()
        }
        root.findViewById<TextView>(R.id.text_dashboard).setOnClickListener {
            view:View->
            view.findNavController().navigate(R.id.action_global_globalFragment)
        }
        return root
    }
}