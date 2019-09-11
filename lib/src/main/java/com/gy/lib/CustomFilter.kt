package com.gy.lib

import android.util.Log
import android.widget.BaseAdapter
import android.widget.Filter
import com.gy.lib.bean.BaseItem
import java.util.*


class CustomFilter<T : BaseItem>(var data: ArrayList<T>, var adapter: BaseAdapter) : Filter() {

     var currentValue : ArrayList<T>? = null ;

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var filterResult = FilterResults()
        if(constraint == null||constraint.length ==0){
            var list = ArrayList<T>()
            synchronized(this) {
                list = data
            }
            filterResult.values = list
            filterResult.count = list.size
        }else{
            var prefix = constraint.toString().toLowerCase()
            var newValue = ArrayList<BaseItem>()
            data.forEach {

                if(it.label.contains(prefix)){
                    newValue.add(it)
                }
            }
            filterResult.values = newValue
            filterResult.count = newValue.size
        }
        return filterResult;
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        currentValue = results?.values as ArrayList<T>;
        if (results?.count > 0) {
             adapter.notifyDataSetChanged()
        } else {
            adapter.notifyDataSetInvalidated()
        }
    }

    /*定义最终显示到AutoCompleteTextView 上的文字*/
    override fun convertResultToString(resultValue: Any?): CharSequence {
        resultValue as BaseItem;
        return resultValue.label
    }

}