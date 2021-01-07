package com.rebel.foodrunner.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.rebel.foodrunner.R
import com.rebel.foodrunner.adapter.MenuRecyclerAdapter
import com.rebel.foodrunner.model.Restaurant
import com.rebel.foodrunner.util.ConnectionManager
import org.json.JSONException

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {
    lateinit var recyclerMenu: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: MenuRecyclerAdapter
    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    val resInfoList= arrayListOf<Restaurant>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_menu, container, false)
        recyclerMenu=view.findViewById(R.id.recyclerMenu)
        progressLayout=view.findViewById(R.id.progressLayout)
        progressBar=view.findViewById(R.id.progressBar)
        progressLayout.visibility=View.VISIBLE
        layoutManager= LinearLayoutManager(activity)
        val queue= Volley.newRequestQueue(activity as Context)
        val url="http://13.235.250.119/v2/restaurants/fetch_result/"
        if(ConnectionManager().checkConnectivity(activity as Context)){
            val jsonObjectRequest=object:JsonObjectRequest(Request.Method.GET,url,null, Response.Listener{
                try {
                    progressLayout.visibility = View.GONE
                    val data=it.getJSONObject("data")
                        val success = data.getBoolean("success")
                        if (success) {
                            val resArray = data.getJSONArray("data")
                            for (i in 0 until resArray.length()) {
                                val resJsonObject = resArray.getJSONObject(i)
                                val resObject=Restaurant(
                                    resJsonObject.getString("name"),
                                    resJsonObject.getString("rating"),
                                    resJsonObject.getString("cost_for_one"),
                                    resJsonObject.getString("image_url")
                                )
                                resInfoList.add(resObject)
                                recyclerAdapter= MenuRecyclerAdapter(activity as Context,resInfoList)
                                recyclerMenu.adapter=recyclerAdapter
                                recyclerMenu.layoutManager=layoutManager
                            }
                        } else {
                            Toast.makeText(
                                activity as Context,
                                "Some Error Occured",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }catch (e: JSONException){
                    Toast.makeText(activity as Context,"Some unexpected error occured", Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener {
                Toast.makeText(activity as Context,"Volley Error occured", Toast.LENGTH_SHORT).show()
            }

            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers=HashMap<String,String>()
                    headers["Content-type"]="application/json"
                    headers["token"]="9bf534118365f1"
                    return headers
                }
            }
            queue.add(jsonObjectRequest)
        }
        else{
            val dialog= AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection not Found")
            dialog.setPositiveButton("Open Settings"){text, listener->
                val settingsIntent= Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                activity?.finish()
            }
            dialog.setNegativeButton("Exit"){text, listener->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()
        }
        return view
    }
}
