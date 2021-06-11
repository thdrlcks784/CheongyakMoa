package com.cheongyakmoa.view.fragment.totalFragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheongyakmoa.view.databinding.ProgressBarBinding

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

    class ProgressBarHolder(private val binding: ProgressBarBinding)
        :RecyclerView.ViewHolder(binding.root){

        }


    //무한스크롤 listener
    class InfiniteScrollListener(viewModel: TotalItemViewModel) : RecyclerView.OnScrollListener(){
        private val vm = viewModel
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
            val itemTotalCount = recyclerView.adapter!!.itemCount-1

            if(!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount){
                vm.lhDataRequest()
            }
        }

    }

}