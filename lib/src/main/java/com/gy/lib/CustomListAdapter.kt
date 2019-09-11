package com.gy.lib

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.gy.lib.bean.BaseItem
import java.util.*

class  CustomListAdapter<T : BaseItem> (var dataList: ArrayList<T>, var callBack: AdapterCallBack, var resLayout:Int) : BaseAdapter(),Filterable{

    val customFilter : CustomFilter<T> ;
    init {
        customFilter = CustomFilter(dataList,this)
    }

    override fun getFilter(): Filter {
        return customFilter;
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = LayoutInflater.from(parent.context).inflate(resLayout,parent,false);
        callBack.initViewContent(view,getValue()[position])
//        view.setOnClickListener {
//            callBack.onItemClick(customFilter.currentValue[position])
//        }
        return view
    }

    override fun getItem(position: Int): BaseItem {
        return getValue().get(position)
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong();
    }

    override fun getCount(): Int {
        return getValue().size
    }

    fun getValue():ArrayList<T>{
        return customFilter.currentValue ?: dataList
    }


}

