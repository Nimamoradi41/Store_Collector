package com.example

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.Models.Response_Safir
import com.example.store_collector.LoginActivity
import com.example.store_collector.R
import com.example.store_collector.adapter_Main_safir
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_frag__one.*
import kotlinx.android.synthetic.main.fragment_frag__one.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Frag_One.newInstance] factory method to
 * create an instance of this fragment.
 */
class Frag_One : BaseFragment() {
    var ad_: adapter_Main_safir?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var View=inflater.inflate(R.layout.fragment_frag__one, container, false)
        ad_= adapter_Main_safir(requireActivity())
        View.recy_One_22.adapter=ad_
        GetOrder()
        View.ref_11.setOnRefreshListener {
            GetOrder()
        }
        return  View
    }




    fun  GetOrder()
    {
        DialLoad()
        var req=api?.GetOrder_Safir("Bearer " +token)
        req?.enqueue(object : Callback<Response_Safir> {
            override fun onResponse(call: Call<Response_Safir>, response: Response<Response_Safir>) {
                Dial_Close()
                ref_11.isRefreshing=false
                Log.i("zcvmzkmvzkmvmzv",response.code().toString())
                if (response.isSuccessful)
                {
                    if(response.body()?.data!=null)
                    {
                        if (response.body()?.data?.orderSendingStatus==null)
                        {
                            Log.i("astdatdsa","A")
                            if (ad_?.list!=null)
                            {
                                ad_?.list?.clear()
                                ad_?.notifyDataSetChanged()
                            }
                            no_item_Card_22.visibility= View.VISIBLE
                        }else{
                            Log.i("astdatdsa","B")
                            no_item_Card_22.visibility= View.GONE
                            ad_?.list?.clear()
                            ad_?.list=response.body()?.data?.orderSendingStatus
                            ad_?.notifyDataSetChanged()
                        }

                    }else{
                        if (ad_?.list!=null)
                        {
                            ad_?.list?.clear()
                            ad_?.notifyDataSetChanged()
                        }
                        no_item_Card_22.visibility= View.VISIBLE
                    }
                }
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean,Type: Boolean) {
                            if (success)
                            {
                                GetOrder()
                            }else{
                                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                                activity?.finish()
                            }
                        }

                    })
                }
            }
            override fun onFailure(call: Call<Response_Safir>, t: Throwable) {
                Dial_Close()
                ref_11.isRefreshing=false
                var I=3
                var p=   Dialapp(I,"لطفا دوباره تلاش کنید",object : Dial_App.Interface_new{
                    override fun News() {

                    }
                },requireActivity())
                p.show()

            }

        })
    }



}