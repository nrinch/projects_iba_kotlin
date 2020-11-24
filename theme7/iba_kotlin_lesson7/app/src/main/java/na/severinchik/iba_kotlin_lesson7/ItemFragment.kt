package na.severinchik.iba_kotlin_lesson7

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.snackbar.Snackbar
import na.severinchik.iba_kotlin_lesson7.dummy.DummyContent


/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment(), PopupMenu.OnMenuItemClickListener {
    var string: String = ""
    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        var recyclerView = view.findViewById<RecyclerView>(R.id.list);

        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = MyItemRecyclerViewAdapter(DummyContent.ITEMS)
        }
        var button = view.findViewById<Button>(R.id.pop_up_button)
        button.setOnClickListener { v ->
            PopupMenu(requireContext(), v).apply {
                setOnMenuItemClickListener(this@ItemFragment)
                inflate(R.menu.popup)
                show()
                for (i in (1..1000000)) {
                    string + "1"
                    string + "1"
                    string + "1"

                }
            }

        }
        return view
    }

//    override fun onPrepareOptionsMenu(menu: Menu) {
//        val alertMenuItem = menu.findItem(R.id.main_activity_item1)
//        val viewGroup = alertMenuItem.actionView
//        viewGroup
//            .findViewById<TextView>(R.id.item_number)
//            .setTextColor(requireActivity().getColor(R.color.purple_200))
//    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        val v = item.actionView

        return when (item.itemId) {
            R.id.main_activity_item1 -> {
                Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()

                true
            }
            R.id.main_activity_item2 -> {
                Toast.makeText(requireContext(), item.title, Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                false
            }
        }
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.context_menu_select -> {

                Snackbar.make(
                    requireActivity().findViewById(R.id.content),
                    "Select ${item.order}",
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setBackgroundTint(requireActivity().getColor(R.color.teal_200))
                    .setActionTextColor(requireActivity().getColor(R.color.purple_200))
                    .setTextColor(requireActivity().getColor(R.color.white))
                    .setAction("Dismiss", View.OnClickListener {

                    }).show()
                Snackbar.make(
                    requireActivity().findViewById(R.id.content),
                    "jkhdkjfhs ${item.order}",
                    Snackbar.LENGTH_INDEFINITE
                ).setBackgroundTint(requireActivity().getColor(R.color.teal_200))
                    .setActionTextColor(requireActivity().getColor(R.color.purple_200))
                    .setTextColor(requireActivity().getColor(R.color.white))
                    .setAction("Dismiss", View.OnClickListener {

                    }).show()
                true
            }
            else -> super.onContextItemSelected(item)

        }
    }


    companion object {


        const val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}

