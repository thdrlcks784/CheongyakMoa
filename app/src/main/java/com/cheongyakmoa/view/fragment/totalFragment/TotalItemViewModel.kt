package com.cheongyakmoa.view.fragment.totalFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheongyakmoa.view.dto.Item
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class TotalItemViewModel : ViewModel(){

    private val _itemList = MutableLiveData<List<Item>>()
    private val itemArr = arrayListOf<Item>()
    val itemList: LiveData<List<Item>>
        get() = _itemList



    fun onStart() {
        showItemList()
    }

    private fun showItemList() {
        lhDataRequest()
    }


    fun lhDataRequest(){

        val url = "http://apis.data.go.kr/B552555/lhNoticeInfo/getNoticeInfo?serviceKey=kaSHBmdu6J6CHyCoEnJzyueeR2YEx%2F0hldHc2jDdBBQL%2FIkBOf58bLkUXioX5YcRrFGXBEiqCEKvC52YzYJ%2FeA%3D%3D&PG_SZ=20&PAGE=1"


        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("TAG", "리퀘스트 실패")
            }
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()!!.string()

                val jsonobj: JSONArray = JSONArray(body.toString()).getJSONObject(1).getJSONArray("dsList")
                var size: Int = jsonobj.length()
                for(i in 0.. size-1){
                    var json_objdetail : JSONObject = jsonobj.getJSONObject(i)
                    var item : Item = Item(
                        json_objdetail.getString("DEP_NM"),
                        json_objdetail.getString("AIS_TP_CD_NM"),
                        json_objdetail.getString("BBS_TL")
                    )
                    Log.d("TAG","test$item")
                    itemArr.add(item)
                }
                _itemList.postValue(itemArr)

            }

        })

    }
}