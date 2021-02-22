package com.example.store_collector

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.Login
import com.example.MainActivity_Safir
import com.example.Models.ErrorCode500
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextNumber
import kotlinx.android.synthetic.main.activity_login_2.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoginActivity : BaseActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        setContentView(R.layout.activity_login_2)
        window.statusBarColor=Color.parseColor("#6D63FF")
        button12.setOnClickListener {
            if (editTextTextPersonName4.text.trim().toString().isNullOrEmpty())
            {
                Snackbar.make(holder_2, "نام کاربری را وارد کنید", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (editTextTextPersonName.text.trim().toString().isNullOrEmpty())
            {
                Snackbar.make(holder_2, "رمز عبور خود را وارد کنید", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            Confirm()



        }
    }

    fun  Confirm()
    {
        if (isNetConnected())
        {
            DialLoad()
            UserName=editTextTextPersonName4.text.trim().toString()
            Password=editTextTextPersonName.text.trim().toString()
            var json=""
            json= JSONObject().put("userName", UserName)
                .put("password", Password)
                .put("type", 1)
                .put("seriall", "Android")
                .put("appVersion", 1)
                .put("osType", 1).toString()
//                     .put("phone", "").toString()
            var body= RequestBody.create(MediaType.parse("text/plain"), json)
            var call=api?.ConfirmUser(body)
            call?.enqueue(object : Callback<ConfirmSmsResponse> {
                override fun onResponse(
                    call: Call<ConfirmSmsResponse>, response: Response<ConfirmSmsResponse>
                ) {
                    Dial_Close()
                    if (response.code() == 500) {
                        var code500: ErrorCode500? = null
                        val gson = Gson()
                        val adapter: TypeAdapter<ErrorCode500> =
                            gson.getAdapter(ErrorCode500::class.java)
                        try {
                            if (response.errorBody() != null) code500 = adapter.fromJson(
                                response.errorBody()!!.string()
                            )
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        Toast.makeText(
                            this@LoginActivity,
                            "" + code500?.getMessage(),
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                    if (response.isSuccessful)
                    {
                        if (response.body()?.data!=null)
                        {
                            if (response.body()?.isSuccess!!)
                            {
                                if (response.body()?.data?.status==2)
                                {
                                    sharedPreferences?.edit()?.putString(Constanc.USER_SECURITY_KEY, response.body()?.data!!.securityKey)?.apply()
                                    securityKey= response.body()?.data!!.securityKey
                                    Login(securityKey,object  :Login{
                                        override fun onLoginCompleted(success: Boolean,Type:Boolean) {
                                            if (success)
                                            {

                                                if (Type)
                                                {
                                                    Log.i("toldmsfgth","3")
                                                    startActivity(Intent(this@LoginActivity,
                                                            MainActivity_Safir::class.java))
                                                    finish()
                                                }else{
                                                    Log.i("toldmsfgth","4")
                                                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                                                    finish()
                                                }
                                            }else{
                                                Snackbar.make(holder_2, "مشکلی در ورود به سیستم به وجود آمده است", Snackbar.LENGTH_SHORT).show()
                                            }
                                        }

                                    })
                                }else{
                                    Snackbar.make(holder_2, "اطلاعات کاربری را اشتباه وارد کردید", Snackbar.LENGTH_SHORT).show()
                                }

                            }

                        }

//                        var i= Intent(activity, MultyActivity_2::class.java)
//                        i.putExtra("Type", "Z")
//                        i.putExtra("data", phoneNumber)
//                        startActivity(i)
//                        activity?.finish()

                    }


                }

                override fun onFailure(call: Call<ConfirmSmsResponse>, t: Throwable) {
                    Dial_Close()
                    Log.i("dvkmfkvkdmvs", t.message.toString())
                    Snackbar.make(holder_2, "دوباره تلاش کنید", Snackbar.LENGTH_SHORT).show()
                }

            })




        }else{
            Snackbar.make(holder_2, "اتصال خود را به اینترنت چک کنید", Snackbar.LENGTH_SHORT).show()
        }
    }



}