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
import kotlinx.android.synthetic.main.fragment_frag__one.*
import kotlinx.android.synthetic.main.fragment_frag__one.ref_11
import kotlinx.android.synthetic.main.fragment_frag__one.view.*
import kotlinx.android.synthetic.main.fragment_frag__one.view.recy_One_22
import kotlinx.android.synthetic.main.fragment_frag__two.*
import kotlinx.android.synthetic.main.fragment_frag__two.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Frag_Two.newInstance] factory method to
 * create an instance of this fragment.
 */
class Frag_Two : BaseFragment() {
    var ad_: adapter_Main_safir?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var View= inflater.inflate(R.layout.fragment_frag__two, container, false)
        ad_= adapter_Main_safir(requireActivity())
        View.recy_One_23.adapter=ad_
        GetOrder(View)
        View.ref_12.setOnRefreshListener {
            GetOrder(View)
        }


        return  View
    }

    fun  GetOrder(V:View)
    {
        DialLoad()
        var req=api?.GetOrder_Safir("Bearer " +token)
        req?.enqueue(object : Callback<Response_Safir> {
            override fun onResponse(call: Call<Response_Safir>, response: Response<Response_Safir>) {
                Dial_Close()
                ref_12.isRefreshing=false
                Log.i("zcvmzkmvzkmvmzv",response.code().toString())
                if (response.isSuccessful)
                {
                    if(response.body()?.data!=null)
                    {
                        if (response.body()?.data?.orderAbsenceStatus==null)
                        {
                            if (ad_?.list?.clear()!=null)
                            {
                                ad_?.list?.clear()
                                ad_?.notifyDataSetChanged()
                            }

                            Log.i("svkmskdvsd","W")
                            V.no_item_Card_23.visibility= View.VISIBLE
                        }else{
                            Log.i("svkmskdvsd","Y")
                            V. no_item_Card_23.visibility= View.GONE
                            ad_?.list?.clear()
                            ad_?.list=response.body()?.data?.orderAbsenceStatus
                            ad_?.notifyDataSetChanged()
                        }

                    }else{
                        if (ad_?.list?.clear()!=null)
                        {
                            Log.i("svkmskdvsd","B")
                            ad_?.list?.clear()
                            ad_?.notifyDataSetChanged()
                        }




                        Log.i("svkmskdvsd","A")
                        V. no_item_Card_23.visibility= View.VISIBLE
                    }
                }
                if (response.code()==401)
                {
                    Login(securityKey,object : Login {
                        override fun onLoginCompleted(success: Boolean,Type: Boolean) {
                            if (success)
                            {
                                GetOrder(V)
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
                ref_12.isRefreshing=false
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