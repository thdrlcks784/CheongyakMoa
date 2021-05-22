package com.cheongyakmoa.view.fragment.totalFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheongyakmoa.view.dto.Item

class TotalItemViewModel : ViewModel(){

    private val _itemList = MutableLiveData<List<Item>>()
    private val itemArr = arrayListOf<Item>()
    val itemList: LiveData<List<Item>>
        get() = _itemList

    fun onStart() {
        showItemList()
    }

    private fun showItemList() {
        for(i in 1..100){
            addItem(Item("$i"))
        }
        _itemList.value = itemArr
    }

    private fun addItem(item : Item){
        itemArr.add(item)
    }
}