package com.example.store_collector

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.animation.doOnEnd
import com.example.Dial_App
import com.example.Login
import com.example.MainActivity_Safir
import com.google.android.material.snackbar.Snackbar
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : BaseActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        var va= ValueAnimator.ofInt(0,450)
        va.addUpdateListener {
            textView82.layoutParams.width= it.animatedValue as Int
            textView82.requestLayout()
        }
        va.setDuration(2000).doOnEnd {
            if (!isNetConnected())
            {
                Log.i("toldmsfgth","1")
                var I=2;
                var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                    override fun News() {
                        finish()
                    }
                },this)
                p.show()
                return@doOnEnd
            }
            if (token.isNullOrEmpty()||securityKey.isNullOrEmpty())
            {
                Log.i("toldmsfgth","78")
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
                Log.i("toldmsfgth","2")
                Login(securityKey,object  : Login {
                    override fun onLoginCompleted(success: Boolean,Type: Boolean) {
                        if (success)
                        {
                            if (Type)
                            {
                                Log.i("toldmsfgth","3")
                                startActivity(Intent(this@SplashScreen,MainActivity_Safir::class.java))
                                finish()
                            }else{
                                Log.i("toldmsfgth","3")
                                startActivity(Intent(this@SplashScreen,MainActivity::class.java))
                                finish()
                            }

                        }else{
                            Log.i("toldmsfgth","4")
                            var I=2;
                            var p=   Dialapp(2,"اتصال خود را به اینترنت بررسی کنید",object : Dial_App.Interface_new{
                                override fun News() {
                                    finish()
                                }
                            },this@SplashScreen)
                            p.show()
                        }
                    }

                })
            }
        }
        va.start()


    }
}