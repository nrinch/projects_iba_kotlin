package na.severinchik.iba_kotlin_lesson8_room

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import na.severinchik.iba_kotlin_lesson8_room.adapters.SpinnerCategoriesAdapter
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Category
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Note
import na.severinchik.iba_kotlin_lesson8_room.databinding.FragmentAddNoteBinding
import na.severinchik.iba_kotlin_lesson8_room.viewmodel.MainViewModel
import na.severinchik.iba_kotlin_lesson8_room.viewmodel.factories.MainViewModelFactory
import java.util.*

class AddNoteFragment : Fragment() {
    lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = MainViewModelFactory(application)
        val mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)

        binding.categoriesIdSpinner.adapter =
            mainViewModel.categories.value?.let {
                context?.let { it1 ->
                    SpinnerCategoriesAdapter(
                        it,
                        it1
                    )
                }
            }
        binding.save.setOnClickListener {
            var calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            Note(
                binding.name.toString(),
                binding.content.toString(),
                calendar.time,
                (binding.categoriesIdSpinner.selectedItem as Category).uid

                )


            activity?.supportFragmentManager?.popBackStack()
        }
        return binding.root

    }

}