package na.severinchik.iba_kotlin_lesson8_room.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import na.severinchik.iba_kotlin_lesson8_room.database.entity.Category
import na.severinchik.iba_kotlin_lesson8_room.databinding.CategorisItemBinding

class CategoriesAdapter :
    ListAdapter<Category, CategoriesAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }

    class CategoryViewHolder private constructor(private val binding: CategorisItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Category) {
            binding.category = item
        }

        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategorisItemBinding.inflate(layoutInflater, parent, false)
                return CategoryViewHolder(binding)
            }
        }
    }

}


class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.uid == newItem.uid
    }


    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }


}