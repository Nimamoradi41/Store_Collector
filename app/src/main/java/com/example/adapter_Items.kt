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

class adapter_Items : RecyclerView.Adapter<adapter_Items.view>() {
    var list :ArrayList<data_3> ?=null;
    init {
        list=ArrayList<data_3>()
    }
    public class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_items,parent,false)
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
            holder.itemView.chr.setText(Item?.productTitle)
        }else{
            holder.itemView.chr.setText("نامشخص")
        }




        holder.itemView.chr.isChecked = Item?.Selected!!



        holder.itemView.chr.setOnClickListener {
           var cc=list?.get(position)
           cc?.Selected = holder.itemView.chr.isChecked
            list?.set(position, cc!!)
        }







    }

    override fun getItemCount(): Int {
     return list?.size!!
    }
}