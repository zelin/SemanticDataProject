package com.aru.sd.assessment.fragments

import android.os.Bundle
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.aru.sd.assessment.BaseActivity
import com.aru.sd.assessment.MyApplication

open class BaseFragment : Fragment() {

    lateinit var mActivity : BaseActivity
    lateinit var app : MyApplication

    lateinit var  indicator : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity;
        app = mActivity.application as MyApplication
    }

}