package com.aru.sd.assessment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aru.sd.assessment.BaseActivity
import com.aru.sd.assessment.R
import kotlinx.android.synthetic.main.adapter_cart_row.view.*
import java.util.*
import kotlin.collections.HashMap

class PriceAdapter(val data : ArrayList<HashMap<String, String>>, val mContext: BaseActivity) : RecyclerView.Adapter<PriceViewHolder>()
{
    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return data.size
    }

    init {
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {

        val holder = PriceViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_store_row, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {

        val item = data.get(position)
        holder.title.text = item.get("name")
        holder.details.text = item.get("id")
        holder.price.text = item.get("currency") + " " + item.get("price")
    }
}

class PriceViewHolder (view: View) : RecyclerView.ViewHolder(view)
{
    // Holds the TextView that will add each animal to
    val image    = view.image
    val title    = view.title
    val details  = view.details
    val price    = view.price

    val rootView = view.rootView
}