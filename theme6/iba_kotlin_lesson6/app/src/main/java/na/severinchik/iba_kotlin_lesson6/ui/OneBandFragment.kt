package na.severinchik.iba_kotlin_lesson6.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import na.severinchik.iba_kotlin_lesson6.R
import na.severinchik.iba_kotlin_lesson6.databinding.FragmentOneBandBinding
import na.severinchik.iba_kotlin_lesson6.ui.band.BandAdapter
import na.severinchik.iba_kotlin_lesson6.ui.band.BandViewModel

class OneBandFragment : Fragment(), BandAdapter.BandClickListener {
    private lateinit var bandViewModel: BandViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOneBandBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_one_band,container,false);
        bandViewModel =
            ViewModelProvider(this).get(BandViewModel::class.java)
        val adapter = BandAdapter(this)
        binding.viewPager.adapter =adapter
        bandViewModel.bands.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }

    override fun onClick(name: String) {
        TODO("Not yet implemented")
    }
}