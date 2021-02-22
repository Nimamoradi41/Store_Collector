package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.example.Models.Model_Action
import com.example.Models.ResGetOrderItem
import com.example.Models.Res_SetAction
import com.example.Models.data_4
import com.example.store_collector.BaseActivity
import com.example.store_collector.Interface_new_2
import com.example.store_collector.LoginActivity
import com.example.store_collector.R
import kotlinx.android.synthetic.main.activity_detail_item.*
import kotlinx.android.synthetic.main.activity_item__detail__sfir.*
import kotlinx.android.synthetic.main.activity_item__detail__sfir_2.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Item_Detail_Sfir : BaseActivity() {
    var data: data_4 ?=null
    var adpter: adapter_Items_2?=null
    var pos:Int=-7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_item__detail__sfir)
        setContentView(R.layout.activity_item__detail__sfir_2)
        data=intent.getSerializableExtra("data") as data_4
//        pos= intent.getIntExtra("Pos", -1)
        adpter= adapter_Items_2()
        dncnvncxv.adapter=adpter
        if (data?.numberTracking!=null)
        {
            textView36.setText(data?.numberTracking)
        }





        textView41.setOnClickListener {
            Log.i("vsnvljasdv", data?.latitude.toString())
            Log.i("vsnvljasdv", data?.longitude.toString())
            val uri = String.format(Locale.ENGLISH, "geo:${data?.longitude},${data?.latitude}?q=address", data?.latitude, data?.longitude)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent)
        }

        if (data?.maxHourCanDelivery!=null)
        {
            textView97.setText(data?.maxHourCanDelivery + " ساعت ")
        }






        if (data?.addressFullName!=null)
        {
            textView37.setText(data?.addressFullName)
        }


        if (data?.addressPeykInfo!=null)
        {
            textView38.setText(data?.addressPeykInfo)
        }

        if (data?.fullLocation!=null)
        {
            textView39.setText(data?.fullLocation)
        }


        if(data?.addressTel!=null)
        {
            textView40.setText(" شماره تماس "+data?.addressTel)
        }else{
            textView40.setText("نامشخص")
        }





        GetDetail(data?.id.toString())
//        if (data?.addressPeykInfo!=null)
//        {
//
//        }





//        textView14.setOnClickListener {
//            val dialIntent = Intent(Intent.ACTION_DIAL)
//            dialIntent.data = Uri.parse("tel:" + "${data?.addressTel}")
//            startActivity(dialIntent)
//        }
        imageView5.setOnClickListener {
            finish()
        }

        fvndsnas.setOnClickListener {
            var p=   Dialog_Ask(6, "آیا مطمعن هستید ؟", object : Interface_new_2() {
                override fun News(s: String) {
                    if (s.equals("A")) {
                        DialLoad()
                        SetAction("1")
                    }
                }

            }, this)
            p.show()

        }




//
//
//
        vkadvad.setOnClickListener {

            var p=   Dialog_Ask(6, "آیا مطمعن هستید ؟", object : Interface_new_2() {
                override fun News(s: String) {
                    if (s.equals("A")) {
                        DialLoad()
                        SetAction("0")
                    }
                }

            }, this)
            p.show()
        }
//
        hcascgsv.setOnClickListener {
            var p=   Dialog_Ask(6, "آیا مطمعن هستید ؟", object : Interface_new_2() {
                override fun News(s: String) {
                    if (s.equals("A")) {
                        DialLoad()
                        SetAction("2")
                    }
                }

            }, this)
            p.show()

        }

//        button.setOnClickListener {
//            if (isNetConnected())
//            {
//                DialLoad()
//                EndCollect(data?.id.toString())
//            }else{
//                var I=3
//                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
//                    override fun News() {
//
//                    }
//                },this@DetailItem)
//                p.show()
//            }
//        }

//        if (data?.fullLocation!=null)
//        {
//            textView4.setText(data?.fullLocation)
//        }
//
//        if (data?.orderStatus==3)
//        {
//            imageView.visibility= View.GONE
//        }
//
//        if (data?.orderStatus==4)
//        {
//            imageView.visibility= View.VISIBLE
//        }
//
//        if (data?.datePaymentFa!=null)
//        {
//            textView6.setText(data?.datePaymentFa)
//        }
//
//
//        textView45.setText((pos + 1).toString())
//
//        if (data?.addressDistance!=null)
//        {
//            textView8.setText(data?.addressDistance)
//        }
//        if (data?.orderStatus==3)
//        {
//            if (isNetConnected())
//            {
//                DialLoad()
////                Start(data?.id.toString())
//            }else{
//                var I=3
//                var p=   Dialapp(I, "لطفا دوباره تلاش کنید", object : Dial_App.Interface_new {
//                    override fun News() {
//                        finish()
//                    }
//                }, this@Item_Detail_Sfir)
//                p.show()
//            }
//        }

//        if (data?.orderStatus==4)
//        {
////            GetOrderItem(data?.id.toString())
//        }










    }


    fun SetAction(Id: String)
    {
        var Body=Model_Action()
        Body.orderId=data?.id
        Body.type=Id
        var req=api?.SetAction("Bearer " + token, Body)
        req?.enqueue(object : Callback<Res_SetAction> {
            override fun onResponse(
                    call: Call<Res_SetAction>,
                    response: Response<Res_SetAction>
            ) {
                Log.i("zcvmzkmvzkmvmzv_2", response.code().toString())
                Dial_Close()
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        setResult(RESULT_OK)
                        finish()
                    }
                }
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean, Type: Boolean) {
                            if (success) {
                                GetDetail(Id)
                            } else {
                                startActivity(
                                        Intent(
                                                this@Item_Detail_Sfir,
                                                LoginActivity::class.java
                                        )
                                )
                                finish()
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<Res_SetAction>, t: Throwable) {
                Dial_Close()
                var I = 3
                var p = Dialapp(I, "لطفا دوباره تلاش کنید", object : Dial_App.Interface_new {
                    override fun News() {

                    }
                }, this@Item_Detail_Sfir)
                p.show()

            }

        })
    }

    fun  GetDetail(Id: String)
    {
        var body= RequestBody.create(MediaType.parse("text/plane"), Id)
        var req=api?.GetOrderItem_Safir("Bearer " + token, body)
        req?.enqueue(object : Callback<ResGetOrderItem> {
            override fun onResponse(
                    call: Call<ResGetOrderItem>,
                    response: Response<ResGetOrderItem>
            ) {
                Log.i("zcvmzkmvzkmvmzv_2", response.code().toString())
                Dial_Close()
                if (response.isSuccessful) {
                    if (response.body()?.data != null) {
                        adpter?.list = response.body()?.data
                        adpter?.notifyDataSetChanged()
                    }
                }
                if (response.code() == 401) {
                    Login(securityKey, object : Login {
                        override fun onLoginCompleted(success: Boolean, Type: Boolean) {
                            if (success) {
                                GetDetail(Id)
                            } else {
                                startActivity(
                                        Intent(
                                                this@Item_Detail_Sfir,
                                                LoginActivity::class.java
                                        )
                                )
                                finish()
                            }
                        }

                    })
                }
            }

            override fun onFailure(call: Call<ResGetOrderItem>, t: Throwable) {
                Dial_Close()
                var I = 3
                var p = Dialapp(I, "لطفا دوباره تلاش کنید", object : Dial_App.Interface_new {
                    override fun News() {

                    }
                }, this@Item_Detail_Sfir)
                p.show()

            }

        })
    }

}