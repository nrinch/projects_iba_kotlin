package na.severinchik.iba_kotlin_lesson6.ui.band

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import na.severinchik.iba_kotlin_lesson6.R
import na.severinchik.iba_kotlin_lesson6.databinding.FragmentBandBinding

class BandFragment : Fragment(), BandAdapter.BandClickListener {

    private lateinit var bandViewModel: BandViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBandBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_band, container, false)
        bandViewModel =
            ViewModelProvider(this).get(BandViewModel::class.java)
        val adapter = BandAdapter(this)
        binding.homeViewModel = bandViewModel
        binding.bandsList.adapter = adapter
        binding.addButton.text = getString(R.string.symbol)
        bandViewModel.bands.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })
        return binding.root
    }

    override fun onClick(name: String){
        bandViewModel.selectedBand.value = name
        findNavController().navigate(R.id.action_navigation_band_to_oneBandFragment)
    }
}