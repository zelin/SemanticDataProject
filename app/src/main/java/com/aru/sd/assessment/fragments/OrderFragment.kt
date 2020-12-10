package com.aru.sd.assessment.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.aru.sd.assessment.R
import kotlinx.android.synthetic.main.fragment_main.*


class OrderFragment : BaseFragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<TextView>(R.id.queryBtn).setOnClickListener {

            val builder: AlertDialog.Builder = AlertDialog.Builder(mActivity)
            builder.setTitle(getString(R.string.select_query))

            val arrayAdapter = ArrayAdapter<String>(mActivity, android.R.layout.select_dialog_singlechoice)
            arrayAdapter.add("Select all orders")
            arrayAdapter.add("Select orders near me")
            arrayAdapter.add("Sorted Highest paid orders")

            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

            builder.setAdapter(arrayAdapter, DialogInterface.OnClickListener { dialog, which ->
                queryBtn.text = arrayAdapter.getItem(which)


            })
            builder.show()

        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}