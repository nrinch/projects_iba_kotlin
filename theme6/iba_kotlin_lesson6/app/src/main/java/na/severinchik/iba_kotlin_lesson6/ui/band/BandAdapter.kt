package na.severinchik.iba_kotlin_lesson6.ui.band

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import na.severinchik.iba_kotlin_lesson6.databinding.BandItemBinding

class BandAdapter(private val bandClickListener: BandClickListener) :
    ListAdapter<Band, BandAdapter.BandViewHolder>(BandDiffCallback()) {

    interface BandClickListener {
        fun onClick(name: String)
    }

    class BandViewHolder(private val binding: BandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Band) {
            binding.band = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandViewHolder {
        val binding = BandItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BandViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {

            bandClickListener.onClick(item.name)
        }

    }


    class BandDiffCallback : DiffUtil.ItemCallback<Band>() {

        override fun areItemsTheSame(oldItem: Band, newItem: Band): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Band, newItem: Band): Boolean {
            return oldItem == newItem
        }

    }
}

