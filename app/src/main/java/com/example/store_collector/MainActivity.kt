package com.example.store_collector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.Dial_App
import com.example.Login
import com.example.Models.ResponseOreder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    var ad_:adapter_Main?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recy_Items.isNestedScrollingEnabled=false
        ad_= adapter_Main(this)

        recy_Items.adapter=ad_

        imageView4.setOnClickListener {
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
        GetOrder()


        Log.i("dvdasv",Fullname)
        textView12.setText(Fullname.toString())
        ref.setOnRefreshListener {
            if (isNetConnected())
            {
                GetOrder()
            }else{
                var p = Dialapp(2, "اتصال خود را به اینترنت بررسی کنید", object : Dial_App.Interface_new {
                    override fun News() {

                    }
                }, this@MainActivity)
                p.show()
            }

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==10)
        {
            if (resultCode== RESULT_OK)
            {
                GetOrder()
            }
        }
    }

  fun  GetOrder()
  {
      DialLoad()
      var req=api?.GetOrder("Bearer " +token)
      req?.enqueue(object : Callback<ResponseOreder> {
          override fun onResponse(call: Call<ResponseOreder>, response: Response<ResponseOreder>) {
              Dial_Close()
              ref.isRefreshing=false
              Log.i("zcvmzkmvzkmvmzv",response.code().toString())
              if (response.isSuccessful)
              {
                    if(response.body()?.data!=null)
                    {
                        if (response.body()?.data?.size==0)
                        {
                            no_item_Card.visibility=View.VISIBLE
                        }else{
                            no_item_Card.visibility=View.GONE
                            ad_?.list?.clear()
                            ad_?.list=response.body()?.data
                            ad_?.notifyDataSetChanged()

                        }

                    }else{
                        no_item_Card.visibility=View.VISIBLE
                    }
              }
              if (response.code()==401)
              {
                  Login(securityKey,object : Login {
                      override fun onLoginCompleted(success: Boolean,Type :Boolean) {
                          if (success)
                          {
                              GetOrder()
                          }else{
                              startActivity(Intent(this@MainActivity,LoginActivity::class.java))
                              finish()
                          }
                      }

                  })
              }
          }
          override fun onFailure(call: Call<ResponseOreder>, t: Throwable) {
              Dial_Close()
              ref.isRefreshing=false
              var I=3
              var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                  override fun News() {

                  }
              },this@MainActivity)
              p.show()

          }

      })
  }

}