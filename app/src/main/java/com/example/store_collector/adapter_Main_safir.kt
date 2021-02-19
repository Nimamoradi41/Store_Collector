package com.example.store_collector

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Item_Detail_Sfir
import com.example.Models.data_2
import com.example.Models.data_4
import com.example.Models.model_safir
import kotlinx.android.synthetic.main.custome_item_main.view.*
import kotlinx.android.synthetic.main.custome_item_main_3.view.*

class adapter_Main_safir(var C:Activity) : RecyclerView.Adapter<adapter_Main_safir.view>() {
    var list:ArrayList<data_4> ?=null
    init {

        list=ArrayList<data_4>()
    }
    public class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_item_main_3,parent,false)
        return  view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
//        holder.itemView.textView45.setText((position+1).toString())
        var Item=list?.get(position)
        holder.itemView.setOnClickListener {
          var I=Intent(C,Item_Detail_Sfir::class.java)
            I.putExtra("data",Item)
            I.putExtra("Pos",position)
            C.startActivityForResult(I,10)
        }


        if (Item?.numberTracking!=null)
        {
            holder.itemView.textView36.setText(Item.numberTracking)
        }


        if (Item?.maxHourCanDelivery!=null)
        {
            holder.itemView.textView97.setText(Item.maxHourCanDelivery+" ساعت ")
        }



        if (Item?.addressFullName!=null)
        {
            holder.itemView.textView37.setText(Item.addressFullName)
        }



        if (Item?.addressPeykInfo!=null)
        {
            holder.itemView.textView38.setText(Item.addressPeykInfo)
        }


        if (Item?.fullLocation!=null)
        {
            holder.itemView.textView39.setText(Item.fullLocation)
        }


        if (Item?.maxDateCanDeliveryFa!=null)
        {
            holder.itemView.textView40.setText(Item.maxDateCanDeliveryFa)
        }















    }

    override fun getItemCount(): Int {
       if (list!=null)
       {
           return list?.size!!
       }
        return 0
    }
}