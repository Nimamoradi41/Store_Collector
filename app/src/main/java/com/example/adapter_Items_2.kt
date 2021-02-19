package com.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Models.data_3
import com.example.store_collector.R
import kotlinx.android.synthetic.main.custome_item_main.view.*
import kotlinx.android.synthetic.main.custome_item_main.view.textView45
import kotlinx.android.synthetic.main.custome_items.view.*
import kotlinx.android.synthetic.main.custome_items.view.chr
import kotlinx.android.synthetic.main.custome_items.view.textView23
//import kotlinx.android.synthetic.main.custome_items.view.checkBox
import kotlinx.android.synthetic.main.custome_items_2.view.*
import kotlinx.android.synthetic.main.custome_items_3.view.*

class adapter_Items_2 : RecyclerView.Adapter<adapter_Items_2.view>() {
    var list :ArrayList<data_3> ?=null;
    init {
        list=ArrayList<data_3>()
    }
    public class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_items_3,parent,false)
        return  view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
        var Item=list?.get(position)
        if (!Item?.count.toString().isNullOrEmpty())
        {
            holder.itemView.textView23.setText(Item?.count.toString()+" عدد ")
        }else{
            holder.itemView.textView23.setText("نامشخص")
        }
        if (!Item?.productTitle.isNullOrEmpty())
        {
            holder.itemView.dcd.setText(Item?.productTitle)
        }else{
            holder.itemView.dcd.setText("نامشخص")
        }
    }

    override fun getItemCount(): Int {
     return list?.size!!
    }
}