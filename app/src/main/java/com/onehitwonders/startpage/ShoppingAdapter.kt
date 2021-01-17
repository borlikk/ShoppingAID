package com.onehitwonders.startpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_row.view.*

class ShoppingAdapter(
    private val shoppingList: List<ShoppingItem>,
    private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shopping_row,
        parent, false)
        return ShoppingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = shoppingList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView.text = currentItem.text
        holder.textView2.text = currentItem.text2
    }

    override fun getItemCount() = shoppingList.size

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val imageView: ImageView = itemView.shoppingImage
        val textView: TextView = itemView.shoppingName
        val textView2: TextView = itemView.shoppingLocation

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}