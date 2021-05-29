package com.cheongyakmoa.view.util

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheongyakmoa.view.fragment.totalFragment.TotalRecyclerViewAdapter
import com.cheongyakmoa.view.dto.Item

object AdapterBindings {

    const val TEST = "test@123"


    @JvmStatic
    @BindingAdapter("bind:item")
    fun bindItem(recyclerView: RecyclerView, itemList: List<Item>?) {
        Log.d(TEST, "bind:item")
        with(recyclerView.adapter as TotalRecyclerViewAdapter) {
            itemList?.let {
                setItem(it)
            }
        }
    }
}