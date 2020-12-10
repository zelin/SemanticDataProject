package com.periperi.app.widgets

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.airbnb.lottie.LottieAnimationView
import com.aru.sd.assessment.R

class ProgressDialog(private val activity: Activity?) {

    private var builder: AlertDialog.Builder? = null
    private var dialog: AlertDialog? = null
    private var lottieAnimationView: LottieAnimationView? = null
    private var view: View? = null
    var isShowing = false

    fun init(): ProgressDialog {

        return this
    }

    fun show(): AlertDialog? {
        try {
            if (activity != null) {

                val inflater = activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(R.layout.layout_progress, null)

                builder = AlertDialog.Builder(activity, R.style.MyTheme)
                builder!!.setView(view)
                builder!!.setCancelable(false)

                dialog = builder!!.create()
                dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog!!.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent)

                isShowing = true
                dialog!!.show()

                return dialog
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun dismissDialog() {

        try {
            isShowing = false
            if (dialog != null) {
                dialog!!.dismiss()
                dialog!!.hide()
            }
        }catch (e: Exception){}
    }

    companion object {

        fun make(activity: Activity): ProgressDialog {
            val alert = ProgressDialog(activity)
            return alert.init()
        }
    }

}