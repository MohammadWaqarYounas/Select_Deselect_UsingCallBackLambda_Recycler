package com.example.multiselectioncallbackrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val list: List<String>, private val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    // variable to keep track of selected items
    val selectedItems = ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text)
        fun bind(data: String, onClick: (position: Int) -> Unit) {
            textView.text = data

            // check if item is selected and set background accordingly
            if (selectedItems.contains(adapterPosition)) {
                itemView.setBackgroundResource(R.drawable.interest_red_border)
                textView.setTextColor(itemView.context.resources.getColor(R.color.red))
            } else {
                itemView.setBackgroundResource(R.drawable.interests_black_border)
                textView.setTextColor(itemView.context.resources.getColor(R.color.black))
            }

            // set onClickListener for each item
            itemView.setOnClickListener {
                val position = adapterPosition
                onClick(position)
                if (selectedItems.contains(position)) {
                    selectedItems.remove(position)
                } else {
                    selectedItems.add(position)
                }
                notifyItemChanged(position)
            }
        }
    }
}
