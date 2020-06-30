package com.example.eventreminder.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventreminder.R
import com.example.eventreminder.database.Birthday

/**
 * Adapter class to create view holders and supply with data for the birthday list
 * Adapts Birthday object to be used by RecycleView
 */
class BirthdayListAdapter : RecyclerView.Adapter<BirthdayListAdapter.ViewHolder>() {
    // variable to hold the list data
    var data = listOf<Birthday>()
       // notify RecycleView when data has changed and trigger redrawing
       set(value) {
           field = value
           notifyDataSetChanged()
       }

    // called when RecycleView needs a view holder to represent an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    // display data for one list item at specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    // return size of the list of Birthdays in data
    override fun getItemCount() = data.size

    // contains the view information for displaying one item from the item's layout
    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // references to views in birthday list item
        private val itemName: TextView = itemView.findViewById(R.id.list_name)
        private val itemBirthday: TextView = itemView.findViewById(R.id.list_birthday)
        private val itemImage: ImageView = itemView.findViewById(R.id.list_image)

        // function to bind list item text views to database values
        fun bind(item: Birthday) {
            itemName.text = item.name
            itemBirthday.text = item.birthday
            itemImage.setImageResource(R.drawable.ic_cake_blue)
        }

        // called on the ViewHolder class, not a ViewHolder instance
        companion object {
            // function to inflate list item view
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.birthday_list_item, parent, false)

                return ViewHolder(view)
            }
        }

    }
}