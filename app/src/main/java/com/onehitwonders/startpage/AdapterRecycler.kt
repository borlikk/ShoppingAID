package com.onehitwonders.startpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*

class AdapterRecycler(private val exampleList: List<RecyclerItem>) : RecyclerView.Adapter<AdapterRecycler.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,
        parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView.text = currentItem.text1
    }

    override fun getItemCount() = exampleList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val textView: TextView = itemView.card_text

    }
}