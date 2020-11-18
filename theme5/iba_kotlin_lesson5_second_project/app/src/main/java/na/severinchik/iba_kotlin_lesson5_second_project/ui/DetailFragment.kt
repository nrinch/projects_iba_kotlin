package na.severinchik.iba_kotlin_lesson5_second_project.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import na.severinchik.iba_kotlin_lesson5_second_project.R


class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        args.let {
            var drawable = resources.getDrawable(args.photo)

            root.findViewById<ImageView>(R.id.photo).setImageDrawable(drawable)
            root.findViewById<TextView>(R.id.about).text = args.about
        }
        return root
    }


}


