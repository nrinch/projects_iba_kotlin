package na.severinchik.iba_kotlin_lesson_14

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RocketAdapter(var context: Context, mItems: List<RocketAnimationItem>) :
    RecyclerView.Adapter<RocketAdapter.RocketViewHolder>() {
    private var items: List<RocketAnimationItem> = mItems;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_list_item_1, parent, false)

        return RocketViewHolder(view)
    }

    override fun onBindViewHolder(holder: RocketAdapter.RocketViewHolder, position: Int) {

        holder.mTitle.text = items!![position].string
    }


    override fun getItemCount(): Int = items.size


    inner class RocketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTitle: TextView = itemView.findViewById<View>(R.id.text1) as TextView

        init {
            mTitle.setOnClickListener {
                context.startActivity(
                    items[adapterPosition].intent
                )
            }

        }
    }
}