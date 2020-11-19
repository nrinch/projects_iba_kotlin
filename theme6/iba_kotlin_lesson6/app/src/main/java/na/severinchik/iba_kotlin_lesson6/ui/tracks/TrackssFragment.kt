package na.severinchik.iba_kotlin_lesson6.ui.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import na.severinchik.iba_kotlin_lesson6.R
import na.severinchik.iba_kotlin_lesson6.databinding.FragmentTracksBinding
import na.severinchik.iba_kotlin_lesson6.ui.band.Band

class TrackssFragment : Fragment() {

    private lateinit var notificationsViewModel: TrackViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(TrackViewModel::class.java)
        val binding: FragmentTracksBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tracks, container, false)
        binding.trackViewModel = notificationsViewModel
//        binding.spinner.adapter = context?.let { TrackAdapter(listOf("Paranoid", "WarPigs"), it) }
//        binding.spinner.adapter = context?.let { ArrayAdapter<String>(it,R.array.list) }
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            it.let {
                binding.textNotifications.text = it
            }
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}