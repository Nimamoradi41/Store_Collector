package com.example

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.Models.ErrorCode500
import com.example.Models.LoginResponse
import com.example.store_collector.*
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.custome_dial_app.view.*
import kotlinx.android.synthetic.main.custome_dial_app_2.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

open class BaseFragment : Fragment() {
    var sharedPreferences: SharedPreferences? = null
    var mytag_sharedpreferences="Shop_Collector"
    var UserName = ""
    var Fullname = ""
    var Password = ""
    var api: Api?= null
    var token = ""
    var securityKey = ""
    var Dialog_load: Dialog?=null
    fun isNetConnected(): Boolean {
        val cn =activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nf = cn.activeNetworkInfo
        return if (nf != null && nf.isConnected) {
            true
        } else {
            try {
                //                showAlertDialog_new(R.drawable.ic_icons, "ارتباط اینترنت شما قطع است . لطفا آن را بررسی کنید", 0, null);
            } catch (e: java.lang.Exception) {
            }
            false
        }
    }
    public fun DialLoad( ) {
        Dialog_load = Dialog(requireActivity())
        Dialog_load?.setCancelable(false)
        val inflater = LayoutInflater.from(requireActivity())
        val view: View = inflater.inflate(R.layout.layout_loading, null, false)
        Dialog_load?.setContentView(view)
        Dialog_load?.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT)
        Dialog_load?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Dialog_load?.show()






    }
    public fun Dial_Close() {
        if (Dialog_load!=null)
        {
            Dialog_load?.dismiss()
        }
    }
    public fun Dialog_Ask(Type: Int, S: String, I: Interface_new_2, context: Context):Dialog {
        var d = Dialog(context)
        val inflater = LayoutInflater.from(context)
        d.setCancelable(false)
        val view: View = inflater.inflate(R.layout.custome_dial_app_2, null, false)
        view.textView2222.setText(S)
        d.setContentView(view)
        d.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT)
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if (Type==2)
        {
            view.textView22.setText(S)
            view.button7.setText("متوجه شدم")
            view.imageView10dv.setImageResource(R.drawable.ic_bad_req)
        }



        if (Type==3)
        {
            view.textView22.setText(S)
            view.button7.setText("تلاش دوباره")
            view.imageView10dv.setImageResource(R.drawable.ic_refresh)
        }


        view.button113.setOnClickListener {
            d.dismiss()
            I.News("B")
        }

        view.button7s.setOnClickListener {
            d.dismiss()
            I.News("A")
        }
        return d
    }
    public fun Dialapp(Type: Int, S: String, I: Dial_App.Interface_new, context: Context): Dialog {
        var d = Dialog(context)
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custome_dial_app, null, false)
        d.setContentView(view)
        d.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT)
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if (Type==2)
        {
            view.textView22.setText(S)
            view.button7.setText("متوجه شدم")
            view.imageView10.setImageResource(R.drawable.ic_bad_req)
        }


        if (Type==3)
        {
            view.textView22.setText(S)
            view.button7.setText("تلاش دوباره")
            view.imageView10.setImageResource(R.drawable.ic_refresh)
        }

        view.button7.setOnClickListener {
            d.dismiss()
            I.News()
        }
        return d
    }
    fun Login(Seacurity: String, loging: Login)
    {
        var json = ""
        json = JSONObject()
            .put("SecurityKey", securityKey)
            .put("DeviceType", "1")
//            .put("password", Password)
//            .put("userName", UserName)
            .put("AppVersion", "1")
            .put("Imei", "reza")
            .toString()
        var textBody = RequestBody.create(MediaType.parse("text/plain"), json)
        var req: Call<LoginResponse> = api!!.login(textBody)
        req.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.i("djdjcjdcnc", response.code().toString())
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
                        activity,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.isSuccessful) {
//                    Log.i("dvdgfghjkikgfgdfsds", "200")
//                    var Data = response.body()?.data
//                    Log.i("nimamorafigurg", Data?.securityKey.toString())
//                    Log.i("mkoptr", Data?.token.toString())
                    sharedPreferences?.edit()?.putString(Constanc.USER_TOKEN, response.body()?.data?.token)?.apply()
                    sharedPreferences?.edit()?.putString(Constanc.USER_SECURITY_KEY, response.body()?.data?.securityKey)?.apply()
                    sharedPreferences?.edit()?.putString(Constanc.USER_FULLNAME, response.body()?.data?.fullName)?.apply()
//                    sharedPreferences?.edit()?.putString(Constanc.USER_SECURITY_KEY, response.body()?.data?.securityKey)?.apply()
//                    Log.i("dvdasv_2", response.body()?.data?.fullName.toString())
                    Fullname=response.body()?.data?.fullName.toString()
                    securityKey = response.body()?.data?.securityKey.toString()
                    token = response.body()?.data?.token.toString()
                    if (response.body()?.data?.type==3)
                    {
                        loging.onLoginCompleted(true,true)
                    }else{
                        loging.onLoginCompleted(true,false)
                    }

                }
                else {
                    startActivity(Intent(activity, LoginActivity::class.java))
                   activity?.finish()
//                    Log.i("dvdgfghjkikgfgdfsds", "not200")
//                    var i = Intent(this@BaseActivity, MultyActivity_2::class.java)
//                    i.putExtra("Type", "W")
//                    startActivity(i)
//                    finish()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loging.onLoginCompleted(false,false)
                var p = Dialapp(2, "اتصال خود را به اینترنت بررسی کنید", object : Dial_App.Interface_new {
                    override fun News() {

                    }
                }, requireActivity())
                p.show()
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences(mytag_sharedpreferences,
            AppCompatActivity.MODE_PRIVATE
        )
        Update_Data()
    }
    private fun Update_Data() {
        token = sharedPreferences?.getString(Constanc.USER_TOKEN, "").toString()
        UserName= sharedPreferences!!.getString(Constanc.USER_FULLNAME, "").toString()
        Fullname= sharedPreferences!!.getString(Constanc.USER_FULLNAME, "").toString()
        Password = sharedPreferences!!.getString(Constanc.PASSWORD, "")!!
        securityKey = sharedPreferences!!.getString(Constanc.USER_SECURITY_KEY, "")!!
        api= AppStart.getApi()
    }
}