package com.cheongyakmoa.view.fragment.totalFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.cheongyakmoa.view.databinding.VerticalViewItemBinding
import com.cheongyakmoa.view.dto.Item

class TotalRecyclerViewAdapter(private var items: List<Item>)
    :RecyclerView.Adapter<TotalRecyclerViewAdapter.VerticalViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        return VerticalViewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .run {
                VerticalViewHolder(this)
            }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        holder.bind(items[position])
    }


    fun setItem(itemList: List<Item>) {
        items = itemList
        notifyDataSetChanged()
    }

    // ViewHolder
    class VerticalViewHolder(private val binding: VerticalViewItemBinding)
        :RecyclerView.ViewHolder(binding.root){

        fun bind(item: Item) {
            binding.item = item
        }
    }
}