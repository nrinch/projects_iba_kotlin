package na.severinchik.iba_kotlin_lesson8_room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import na.severinchik.iba_kotlin_lesson8_room.adapters.CategoriesAdapter
import na.severinchik.iba_kotlin_lesson8_room.adapters.NoteAdapter
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Category
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Note
import na.severinchik.iba_kotlin_lesson8_room.databinding.FragmentMainBinding
import na.severinchik.iba_kotlin_lesson8_room.utils.DateTypeConverter
import na.severinchik.iba_kotlin_lesson8_room.viewmodel.MainViewModel
import na.severinchik.iba_kotlin_lesson8_room.viewmodel.factories.MainViewModelFactory


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val viewModelFactory = MainViewModelFactory(application)
        val mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val noteAdapter = NoteAdapter()
        val categoriesAdapter = CategoriesAdapter()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this

        binding.categories.adapter = categoriesAdapter
        binding.notes.adapter = noteAdapter

        mainViewModel.categories.observe(viewLifecycleOwner, Observer {
            it?.let {
                categoriesAdapter.submitList(it)
                categoriesAdapter.notifyDataSetChanged()
            }

        })

        mainViewModel.notes.observe(viewLifecycleOwner, {
            it?.let {
                noteAdapter.submitList(it)
                noteAdapter.notifyDataSetChanged()
            }
        })

        binding.addNote.setOnClickListener {
//            findNavController().navigate(R.id.action_global_addNoteFragment)
            mainViewModel.insertNote(Note("Lesson 8 coroutines","Databases",DateTypeConverter().convertToDate(System.currentTimeMillis()),0))

        }
        mainViewModel.getItemTouchHelperCategoriesCallback()?.let {
            ItemTouchHelper(it).attachToRecyclerView(
                binding.notes
            )
        }


        return binding.root
    }


}