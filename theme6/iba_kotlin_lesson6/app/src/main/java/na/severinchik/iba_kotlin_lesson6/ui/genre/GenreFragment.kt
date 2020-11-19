package na.severinchik.iba_kotlin_lesson6.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import na.severinchik.iba_kotlin_lesson6.R

class GenreFragment : Fragment() {

    val genres = arrayOf("Jazz","Blues","Rock'n'Roll","Metal","Pop","Indie","Blues","Rock'n'Roll","Metal","Pop","Indie","Blues","Rock'n'Roll","Metal","Pop","Indie")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_genre, container, false)
        var recyclerView = root.findViewById<RecyclerView>(R.id.genre_list)
        recyclerView.adapter = GenreAdapter(genres)
        return root
    }
}