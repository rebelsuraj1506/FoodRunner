package com.rebel.foodrunner.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rebel.foodrunner.R

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile, container, false)
        val tvProfileName=view.findViewById<TextView>(R.id.tvProfileName)
        val tvProfilePhone=view.findViewById<TextView>(R.id.tvProfilePhone)
        val tvProfileEmail=view.findViewById<TextView>(R.id.tvProfileEmail)
        val tvProfileLocation=view.findViewById<TextView>(R.id.tvProfileLocation)
        sharedPreferences=activity!!.getSharedPreferences("Food Preferences", Context.MODE_PRIVATE)
        tvProfileName.text=sharedPreferences.getString("name","Error")
        tvProfilePhone.text=sharedPreferences.getString("phone","Error")
        tvProfileEmail.text=sharedPreferences.getString("email","Error")
        tvProfileLocation.text=sharedPreferences.getString("address","Error")
        return view
    }

}
