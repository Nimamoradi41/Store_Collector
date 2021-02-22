package com.example.store_collector

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.Dial_App
import com.example.Login
import com.example.Models.ResGetOrderItem
import com.example.Models.ResStartCollect
import com.example.Models.ResponseOreder
import com.example.Models.data_2
import com.example.adapter_Items
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_detail_item.*
import kotlinx.android.synthetic.main.activity_detail_item.textView3
import kotlinx.android.synthetic.main.activity_detail_item_2.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custome_item_main.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailItem : BaseActivity() {
    var data: data_2 ?=null
    var adpter: adapter_Items?=null
    var pos:Int=-7


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail_item)
        setContentView(R.layout.activity_detail_item_2)
        data=intent.getSerializableExtra("data") as data_2
        pos= intent.getIntExtra("Pos",-1)
        adpter= adapter_Items()
        dncnvncxv.adapter=adpter
        if (data?.numberTracking!=null)
        {
            textView36.setText(data?.numberTracking)
        }

        imageView5.setOnClickListener {
            finish()
        }

        if (data?.maxHourCanDelivery!=null)
        {
            textView97.setText(data?.maxHourCanDelivery+" ساعت ")
        }else{
            textView97.setText("نامشخص")
        }


        if (data?.addressFullName!=null)
        {
            textView37.setText(data?.addressFullName)
        }else{
            textView37.setText("نامشخص")
        }



        if (data?.addressPeykInfo!=null)
        {
            textView38.setText(data?.addressPeykInfo)
        }else{
            textView38.setText("نامشخص")
        }




        if (data?.fullLocation!=null)
        {
            textView39.setText(data?.fullLocation)
        }else{
            textView39.setText("نامشخص")
        }




        if (data?.maxDateCanDeliveryFa!=null)
        {
            textView40.setText(" حداکثر زمان تحویل "+data?.maxDateCanDeliveryFa)
        }else{
            textView40.setText("نامشخص")
        }












        fvndsnas.setOnClickListener {

            if (isNetConnected())
            {
                var vvv=true

                adpter?.list?.forEach {
                    if (!it.Selected)
                    {
                        vvv=false
                        return@forEach
                    }
                }
                if (!vvv)
                {
                    Toast.makeText(this@DetailItem,"تیک محصولات را فعال کنید",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
//                DialLoad()
                var p=   Dialog_Ask(6,"آیا مطمعن هستید ؟",object : Interface_new_2() {
                    override fun News(s: String) {
                        if (s.equals("A"))
                        {
                            DialLoad()
                            EndCollect(data?.id.toString())

                        }
                    }

                }, this)
                p.show()



            }else{
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {
                    }
                },this@DetailItem)
                p.show()
            }
        }

//        if (data?.fullLocation!=null)
//        {
//          textView4.setText(data?.fullLocation)
//        }
//
//        if (data?.orderStatus==3)
//        {
//          imageView.visibility=View.GONE
//        }
//
//        if (data?.orderStatus==4)
//        {
//           imageView.visibility=View.VISIBLE
//        }
//
//        if (data?.datePaymentFa!=null)
//        {
//            textView10.setText(data?.datePaymentFa)
//        }


//     textView45.setText((pos+1).toString())


            GetOrderItem(data?.id.toString())

    }

    fun  Start(Id:String)
    {
        var body=RequestBody.create(MediaType.parse("text/plane"),Id)
        var req=api?.StartCollect("Bearer " +token,body)
        req?.enqueue(object : Callback<ResStartCollect> {
            override fun onResponse(call: Call<ResStartCollect>, response: Response<ResStartCollect>) {
                Log.i("zcvmzkmvzkmvmzv_2",response.code().toString())
                Dial_Close()
                if (response.isSuccessful)
                {
                    setResult(RESULT_OK)
                    GetOrderItem(data?.id.toString())
                }
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean,Type :Boolean) {
                            if (success)
                            {
                                Start(Id)
                            }else{
                                startActivity(Intent(this@DetailItem,LoginActivity::class.java))
                                finish()
                            }
                        }

                    })
                }
            }
            override fun onFailure(call: Call<ResStartCollect>, t: Throwable) {
                Dial_Close()
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {
                        finish()
                    }
                },this@DetailItem)
                p.show()

            }

        })
    }

    fun  EndCollect(Id:String)
    {
        var body=RequestBody.create(MediaType.parse("text/plane"),Id)
        var req=api?.EndCollect("Bearer " +token,body)
        req?.enqueue(object : Callback<ResStartCollect> {
            override fun onResponse(call: Call<ResStartCollect>, response: Response<ResStartCollect>) {
                Log.i("zcvmzkmvzkmvmzv_2",response.code().toString())
                Dial_Close()
                if (response.isSuccessful)
                {
                    if (response.body()?.data!!)
                    {
                        setResult(RESULT_OK)
                        finish()
                    }
                }
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean, Type :Boolean) {
                            if (success)
                            {
                                EndCollect(Id)
                            }else{
                                startActivity(Intent(this@DetailItem,LoginActivity::class.java))
                                finish()
                            }
                        }

                    })
                }
            }
            override fun onFailure(call: Call<ResStartCollect>, t: Throwable) {
                Dial_Close()
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {

                    }
                },this@DetailItem)
                p.show()

            }

        })
    }

    fun  GetOrderItem(Id:String)
    {
        var body=RequestBody.create(MediaType.parse("text/plane"),Id)
        var req=api?.GetOrderItem("Bearer " +token,body)
        req?.enqueue(object : Callback<ResGetOrderItem> {
            override fun onResponse(call: Call<ResGetOrderItem>, response: Response<ResGetOrderItem>) {
                Dial_Close()
                Log.i("zcvmzkmvzkmvmzv_2",response.code().toString())
                if (response.isSuccessful)
                {
                    adpter?.list=response.body()?.data
                    adpter?.notifyDataSetChanged()
                    textView21.setText(adpter?.list?.size.toString())
                }
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean,Type :Boolean) {
                            if (success)
                            {
                                GetOrderItem(Id)
                            }else{
                                startActivity(Intent(this@DetailItem,LoginActivity::class.java))
                                finish()
                            }
                        }

                    })
                }
            }
            override fun onFailure(call: Call<ResGetOrderItem>, t: Throwable) {
                Log.i("sdvknalbasfSF", t?.message.toString())
                Log.i("sdvknalbasfSF", t?.toString())
                Dial_Close()
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {
                        finish()
                    }
                },this@DetailItem)
                p.show()

            }

        })
    }
}