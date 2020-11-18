package na.severinchik.iba_kotlin_lesson5_second_project.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import na.severinchik.iba_kotlin_lesson5_second_project.R

class NotificationsFragment : Fragment() {

    val someValue = 42
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        var action =
            NotificationsFragmentDirections.actionNavigationNotificationsToNavigationDashboard()
        action.count = 42
//        root.findViewById<Button>(R.id.b_notifications).setOnClickListener { view: View ->
//            view.findNavController().navigate(action)
//        }
        return root
    }
}