package com.onehitwonders.startpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.loja_row.view.*

class LojaAdapter(private val lojaList: List<LojaItem>,
                  private val listener: OnItemClickListener
): RecyclerView.Adapter<LojaAdapter.LojaViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LojaViewModel {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.loja_row,
        parent, false)

        return LojaViewModel(itemView)
    }

    override fun onBindViewHolder(holder: LojaViewModel, position: Int) {
        val currentItem = lojaList[position]

        holder.imageView.setImageResource(currentItem.image)
        holder.lojaName.text = currentItem.text1
        holder.lojaPiso.text = "Floor: ${currentItem.text2}"
    }

    override fun getItemCount() = lojaList.size

    inner class LojaViewModel(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val imageView: ImageView = itemView.lojaImage
        val lojaName: TextView = itemView.lojaName
        val lojaPiso: TextView = itemView.lojaPiso

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