package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.Models.ResponseOreder
import com.example.Models.Response_Safir
import com.example.store_collector.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.no_item_Card
import kotlinx.android.synthetic.main.activity_main.ref
import kotlinx.android.synthetic.main.activity_main__safir.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_Safir : BaseActivity() {
    var ad_:adapter_Main_safir?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main__safir)
        var adapter =  ViewPagerAdapter(
                getSupportFragmentManager());
        viewpager_3.setAdapter(adapter);
        tabs.setupWithViewPager(viewpager_3);
        tabs.getTabAt(1)?.select()
        imageView4vdv.setOnClickListener {
            var p=   Dialog_Ask(6,"آیا می خواهید از حساب خود خارج شوید ؟",object : Interface_new_2() {
                override fun News(s: String) {
                    if (s.equals("A"))
                    {
                        sharedPreferences?.edit()?.putString(Constanc.USER_TOKEN,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constanc.USER_SECURITY_KEY,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constanc.USER_FULLNAME,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constanc.PASSWORD,"")?.apply()
                        sharedPreferences?.edit()?.putString(Constanc.USERNAME,"")?.apply()
                        finish()
                    }
                }

            }, this)
            p.show()

        }

        textView1dvd2.setText(Fullname)
//        ad_= adapter_Main_safir(this)
//        recy_Items_safir.adapter=ad_
//        GetOrder()
//        Log.i("dvdasv",Fullname)
//        textView12_3.setText(Fullname.toString())
//        ref_2.setOnRefreshListener {
//            if (isNetConnected())
//            {
//                GetOrder()
//            }else{
//                var p = Dialapp(2, "اتصال خود را به اینترنت بررسی کنید", object : Dial_App.Interface_new {
//                    override fun News() {
//
//                    }
//                }, this@MainActivity_Safir)
//                p.show()
//            }
//
//        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==10)
        {
            if (resultCode== RESULT_OK)
            {
//                GetOrder()
            }
        }
    }
//    fun  GetOrder()
//    {
//        DialLoad()
//        var req=api?.GetOrder_Safir("Bearer " +token)
//        req?.enqueue(object : Callback<Response_Safir> {
//            override fun onResponse(call: Call<Response_Safir>, response: Response<Response_Safir>) {
//                Dial_Close()
//                ref_2.isRefreshing=false
//                Log.i("zcvmzkmvzkmvmzv",response.code().toString())
//                if (response.isSuccessful)
//                {
//                    if(response.body()?.data!=null)
//                    {
//                        if (response.body()?.data?.size==0)
//                        {
//                            no_item_Card.visibility= View.VISIBLE
//                        }else{
//                            no_item_Card.visibility= View.GONE
//                            ad_?.list?.clear()
//                            ad_?.list=response.body()?.data
//                            ad_?.notifyDataSetChanged()
//
//                        }
//
//                    }else{
//                        no_item_Card.visibility= View.VISIBLE
//                    }
//                }
//                if (response.code()==401)
//                {
//                    Login(securityKey,object : Login {
//                        override fun onLoginCompleted(success: Boolean,Type: Boolean) {
//                            if (success)
//                            {
//                                GetOrder()
//                            }else{
//                                startActivity(Intent(this@MainActivity_Safir, LoginActivity::class.java))
//                                finish()
//                            }
//                        }
//
//                    })
//                }
//            }
//            override fun onFailure(call: Call<Response_Safir>, t: Throwable) {
//                Dial_Close()
//                ref_2.isRefreshing=false
//                var I=3
//                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
//                    override fun News() {
//
//                    }
//                },this@MainActivity_Safir)
//                p.show()
//
//            }
//
//        })
//    }
}