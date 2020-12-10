package com.aru.sd.assessment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.periperi.app.widgets.ProgressDialog


open class BaseActivity : AppCompatActivity() {

    lateinit var app : MyApplication
    lateinit var  indicator : ProgressDialog
    lateinit var  mContext : BaseActivity

   // lateinit var  indicator : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MyApplication
        mContext = this

        indicator = ProgressDialog.make(mContext)

        //indicator = ProgressBar()
        //val progressBar = findViewById<View>(R.id.progress) as ProgressBar
        //indicator.setIndeterminateDrawable(DoubleBounce())

    }
}