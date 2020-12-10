package com.aru.sd.assessment.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.aru.sd.assessment.R
import com.aru.sd.assessment.adapters.MainAdapter
import com.aru.sd.assessment.adapters.StoreAdapter
import com.hp.hpl.jena.query.*
import com.hp.hpl.jena.tdb.store.Hash
import global.QueryManager
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class StoreFragment : BaseFragment() {
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
            arrayAdapter.add("Select all Stores")
            arrayAdapter.add("Customers with price range")
            arrayAdapter.add("Select customers near me")

            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })

            builder.setAdapter(arrayAdapter, DialogInterface.OnClickListener { dialog, which ->

                queryBtn.text = arrayAdapter.getItem(which)
                var query = ""

                if(which == 0) {
                    query = QueryManager.getAllStores()
                }else if(which == 1) {
                    query = QueryManager.getCustomersWithPriceRange()
                }

                try {
                    val query: Query = QueryFactory.create(query)
                    val qexec: QueryExecution = QueryExecutionFactory.create(query, app.mainModel)

                    val data: ArrayList<HashMap<String, String>> = ArrayList()
                    val idsList : ArrayList<String> = ArrayList()

                    val results = qexec.execSelect()
                    while (results.hasNext())
                    {
                        val sol: QuerySolution = results.nextSolution()
                        val id = sol["entity"].toString().replace("http://www.semanticweb.org/assessment#", "")
                        if (!idsList.contains(id)) {

                            val item: HashMap<String, String> = HashMap()
                            item["id"] = id
                            item["name"] = sol["name"].toString().replace("^^http://www.w3.org/2001/XMLSchema#string", "")
                            item["city"] = sol["city"].toString().replace("^^http://www.w3.org/2001/XMLSchema#string", "")
                            item["country"] = sol["country"].toString().replace("^^http://www.w3.org/2001/XMLSchema#string", "")

                            idsList.add(id)
                            data.add(item)
                        }
                    }
                    qexec.close()

                    view.findViewById<RecyclerView>(R.id.listView).adapter = StoreAdapter(data, mActivity)

                }catch (e: Exception)
                {
                    e.printStackTrace()
                }


            })
            builder.show()

        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}