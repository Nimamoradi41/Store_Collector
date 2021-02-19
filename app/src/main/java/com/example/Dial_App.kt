package com.example

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.store_collector.R
import kotlinx.android.synthetic.main.custome_dial_app.view.*

class Dial_App(var Type: Int, var S: String, var I: Interface_new, context: Context) : Dialog(context) {

    var dialog_holder: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    public fun get():Dialog {
        dialog_holder = Dialog(context)
        dialog_holder!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val inflater = LayoutInflater.from(context)
//        View view = inflater.inflate(R.layout.info_alert_dialog, null);
        //        View view = inflater.inflate(R.layout.info_alert_dialog, null);
        val view: View = inflater.inflate(R.layout.custome_dial_app, null)
        dialog_holder?.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT)
        view.button7.setText(S)
        view.button7.setOnClickListener {
            I.News()
        }
        return dialog_holder as Dialog
    }
    public interface Interface_new{
        public fun News()
    }
}










