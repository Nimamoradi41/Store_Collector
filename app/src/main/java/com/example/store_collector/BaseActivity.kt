package com.example.store_collector

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.FileProvider
import com.example.AppStart
import com.example.Dial_App
import com.example.Login
import com.example.Models.*
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.koushikdutta.ion.Ion
import com.koushikdutta.ion.ProgressCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.custome_dial_app.view.*
import kotlinx.android.synthetic.main.custome_dial_app.view.button7
import kotlinx.android.synthetic.main.custome_dial_app.view.imageView10
import kotlinx.android.synthetic.main.custome_dial_app.view.textView22
import kotlinx.android.synthetic.main.custome_dial_app_2.view.*
import kotlinx.android.synthetic.main.custome_dial_app_2.view.button113
import kotlinx.android.synthetic.main.custome_dial_app_3.view.*
import kotlinx.android.synthetic.main.fragment_frag__one.*
import kotlinx.android.synthetic.main.layout_loading_2.view.*
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException

open class BaseActivity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    var mytag_sharedpreferences="Shop_Collector"
    var UserName = ""
    var Fullname = ""
    var Password = ""
    var api:Api ?= null
    var token = ""
    var Dia_Download : Dialog ?=null
    var securityKey = ""
    var Dialog_load:Dialog?=null
    companion object{
        var api:Api ?= null

    }
    public fun Change_Pass(Old:String,New :String,Dia:Dialog)
    {
        var model= Change_Pass_Model()
        model.newPassword=New
        model.oldPassword=Old
        var  api=api?.Change_Pass("Bearer " +token,model)
        api?.enqueue(object : Callback<Change_Pass_Response> {
            override fun onResponse(call: Call<Change_Pass_Response>, response: Response<Change_Pass_Response>) {
                Dial_Close()
                Dia.dismiss()
//                ref_11.isRefreshing=false
                Log.i("zcvmzkmvzkmvmzv",response.code().toString())
                if (response.isSuccessful)
                {
                    if (response.body()?.data!!)
                    {
                        Toast.makeText(this@BaseActivity,"رمز شما با موفقیت تغییر کرد",Toast.LENGTH_LONG).show()
                        Dia.dismiss()
                    }
                }
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean,Type: Boolean) {
                            if (success)
                            {
                                Change_Pass(Old,New,Dia)
                            }else{
                                startActivity(Intent(this@BaseActivity, LoginActivity::class.java))
                                        finish()
                            }
                        }

                    })
                }
            }
            override fun onFailure(call: Call<Change_Pass_Response>, t: Throwable) {
                Dial_Close()
//                ref_11.isRefreshing=false
                Toast.makeText(this@BaseActivity,"لطفا دوباره تلاش کنید",Toast.LENGTH_LONG).show()

            }

        })
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    fun ChangePass( )
    {
        var d = Dialog(this)
        val inflater = LayoutInflater.from(this)
        d.setCancelable(true)
        val view: View = inflater.inflate(R.layout.custome_dial_app_3, null, false)
        d.setContentView(view)
        d.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        view.button1154.setOnClickListener {
            if (view.editTextTextPersonName4.text.isNullOrEmpty())
            {
                Toast.makeText(this,"رمز قبلی را وارد کنید",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (view.editTextTextPersonName2.text.isNullOrEmpty())
            {
                Toast.makeText(this,"رمز جدید را وارد کنید",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (view.editTextTextPersonName3.text.isNullOrEmpty())
            {
                Toast.makeText(this,"تکرار رمز جدید را وارد کنید",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (view.editTextTextPersonName3.text.length<6)
            {
                Toast.makeText(this,"رمز عبور نباید کمتر از 6 کارکتر",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (view.editTextTextPersonName2.text.length<6)
            {
                Toast.makeText(this,"رمز عبور نباید کمتر از 6 کارکتر",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



            if (view.editTextTextPersonName3.text.toString()!=view.editTextTextPersonName2.text.toString())
            {
                Toast.makeText(this,"رمز جدید با تکرار ان برابر نیست",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            Change_Pass(view.editTextTextPersonName4.text.toString(), view.editTextTextPersonName2.text.toString(),d)
        }

        d.show()
    }

    fun isNetConnected(): Boolean {
        val cn =getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
        Dialog_load = Dialog(this)
        Dialog_load?.setCancelable(false)
        val inflater = LayoutInflater.from(this)
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
        d.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        if (Type==2)
        {
            view.textView2222.setText(S)
            view.button7.setText("متوجه شدم")
            view.imageView10dv.setImageResource(R.drawable.ic_bad_req)
        }



        if (Type==3)
        {
            view.textView2222.setText(S)
            view.button7s.setText("تلاش دوباره")
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
    fun InstallUpdate(result: File) {
        val intent: Intent
        Log.i("ahbahbcabhs","F")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.i("ahbahbcabhs","A")
//            val apkUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID +".fileprovider", result)
            val apkUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID +".contentprovider", result)
            Log.i("ahbahbcabhs","c")
            intent = Intent(Intent.ACTION_INSTALL_PACKAGE)
            intent.data = apkUri
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        } else {
            Log.i("ahbahbcabhs","B")
            val apkUri = Uri.fromFile(result)
            intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive")
            Log.i("ahbahbcabhs","F")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
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
                        this@BaseActivity,
                        "" + code500?.getMessage(),
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }
                if (response.isSuccessful) {
                    if(!response.body()?.data?.appVersion?.allowedToLogin!!)
                    {
                        Dial_Close()
                        Dexter.withActivity(this@BaseActivity).withPermissions(
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ).withListener(object : MultiplePermissionsListener {
                            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                                if (report.areAllPermissionsGranted()) {
                                    if (isNetConnected()) {
                                        Log.i("sdvkadnv","A")
                                        Log.i("svknasnvjdnvaka","True")
                                        val file = File(
                                            Environment.getExternalStoragePublicDirectory(
                                                Environment.DIRECTORY_DOWNLOADS).toString() + "/Store.apk")
                                        if (file.exists()) file.delete()
                                        var url = response.body()?.data?.appVersion?.url
                                        if (!url?.startsWith("http://")!! && !url.startsWith("https://")) url = "http://$url"
                                        Dia_Download= Dialog(this@BaseActivity)
                                        Dia_Download?.setCancelable(false)
                                        val inflater = LayoutInflater.from(this@BaseActivity)
                                        val view_4: View = inflater.inflate(R.layout.layout_loading_2, null, false)
                                        Dia_Download?.setContentView(view_4)
                                        Dia_Download?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
                                            ConstraintLayout.LayoutParams.MATCH_PARENT)
                                        Dia_Download?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                                        Dia_Download?.show()
                                        Ion.with(this@BaseActivity)
                                            .load(url)
                                            .progressHandler(ProgressCallback { downloaded, total ->
                                                var dd=(downloaded*100)/total
                                                view_4.progress_bar_1.setProgress(dd.toInt())
                                                view_4.progressss.setText(dd.toString()+" % ")
                                                Log.i("MyTagg", "onProgress: $downloaded : $total")
                                            })
                                            .write(file)
                                            .setCallback { e, result ->
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                    if (!packageManager.canRequestPackageInstalls()) {
                                                        Log.i("dvnkavjnanjvanv","UIYIYI")
                                                        startActivityForResult(Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).setData(
                                                            Uri.parse(String.format("package:%s", packageName))), 1234)
                                                    } else {
                                                        Log.i("dvnkavjnanjvanv","ASD")
                                                        InstallUpdate(result!!)
                                                    }
                                                } else {
                                                    Log.i("dvnkavjnanjvanv","HJKHKK")
                                                    InstallUpdate(result!!)
                                                }
                                            }
                                    }
                                } else {
                                    if (report.isAnyPermissionPermanentlyDenied) {
                                        Toast.makeText(this@BaseActivity,"با توجه به اینکه شما مجوزهای دسترسی را قبول نکردید ، امکان استفاده از اپلیکیشن وجود ندارد",Toast.LENGTH_LONG).show()
                                    }
                                }
                            }

                            override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                                Toast.makeText(this@BaseActivity,"با توجه به اینکه شما مجوزهای دسترسی را قبول نکردید ، امکان استفاده از اپلیکیشن وجود ندارد",Toast.LENGTH_LONG).show()
                            }
                        }).check()
                    }else{
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
//                    Log.i("dvdgfghjkikgfgdfsds", "200")
//                    var Data = response.body()?.data
//                    Log.i("nimamorafigurg", Data?.securityKey.toString())
//                    Log.i("mkoptr", Data?.token.toString())


                }
                else {
                     startActivity(Intent(this@BaseActivity,LoginActivity::class.java))
                      finish()
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
                        finish()
                    }
                }, this@BaseActivity)
                p.show()
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base2)
        sharedPreferences = getSharedPreferences(mytag_sharedpreferences, MODE_PRIVATE)
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