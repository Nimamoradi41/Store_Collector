package com.example.store_collector

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Models.data_2
import kotlinx.android.synthetic.main.custome_item_main.view.*
import kotlinx.android.synthetic.main.custome_item_main_3.view.*

class adapter_Main(var C:Activity) : RecyclerView.Adapter<adapter_Main.view>() {
    var list:ArrayList<data_2> ?=null
    init {

        list=ArrayList<data_2>()
    }
    public class view(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {
//         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_item_main,parent,false)
         var V=LayoutInflater.from(parent.context).inflate(R.layout.custome_item_main_3,parent,false)
        return  view(V)
    }

    override fun onBindViewHolder(holder: view, position: Int) {
//        holder.itemView.textView45.setText((position+1).toString())
        var Item=list?.get(position)


        holder.itemView.setOnClickListener {
          var I=Intent(C,DetailItem::class.java)
            I.putExtra("data",Item)
            I.putExtra("Pos",position)
            C.startActivityForResult(I,10)
        }



        if (!Item?.maxHourCanDelivery.isNullOrEmpty())
        {
            holder.itemView.textView97.setText(Item?.maxHourCanDelivery+" ساعت ")
        }else{
            holder.itemView.textView97.setText("نامشخص")
        }



        if (!Item?.addressFullName.isNullOrEmpty())
        {
            Log.i("ckava", Item?.fullLocation.toString())
            holder.itemView.textView37.setText(Item?.addressFullName)
        }else{
            holder.itemView.textView37.setText("نامشخص")
        }





        if (!Item?.addressPeykInfo.isNullOrEmpty())
        {
            holder.itemView.textView38.setText(Item?.addressPeykInfo)
        }else{
            holder.itemView.textView38.setText("نامشخص")
        }


        if (!Item?.fullLocation.isNullOrEmpty())
        {
            holder.itemView.textView39.setText(Item?.fullLocation)
        }else{
            holder.itemView.textView39.setText("نامشخص")
        }




        if (!Item?.maxDateCanDeliveryFa.isNullOrEmpty())
        {
            holder.itemView.textView40.setText(Item?.maxDateCanDeliveryFa)
        }else{
            holder.itemView.textView40.setText("نامشخص")
        }


        if (!Item?.numberTracking.isNullOrEmpty())
        {
            holder.itemView.textView36.setText(Item?.numberTracking)
        }else{
            holder.itemView.textView36.setText("نامشخص")
        }










//        if (Item?.numberTracking!=null)
//        {
//            holder.itemView.textView3.setText(Item.numberTracking)
//        }
//
//
//        if (Item?.fullLocation!=null)
//        {
//            holder.itemView.textView4.setText(Item.fullLocation)
//        }
//
//
//        if (Item?.datePaymentFa!=null)
//        {
//            holder.itemView.textView6.setText(Item.datePaymentFa)
//        }
//
//
//
//        if (Item?.addressDistance!=null)
//        {
//            holder.itemView.textView8.setText(Item.addressDistance)
//        }
//
//
//        if (Item?.orderStatus==3)
//        {
//          holder.itemView.imageView.visibility=View.GONE
//        }
//
//        if (Item?.orderStatus==4)
//        {
//            holder.itemView.imageView.visibility=View.VISIBLE
//        }
    }

    override fun getItemCount(): Int {
       if (list!=null)
       {
           return list?.size!!
       }
        return 0
    }
}