package com.rebel.foodrunner.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rebel.foodrunner.R


/**
 * A simple [Fragment] subclass.
 */
class FaqsFragment : Fragment() {
    var menuList=ArrayList<Pair<String,String>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_faqs, container, false)
        val rvFaq=view.findViewById<RecyclerView>(R.id.rvFaq)
        rvFaq.layoutManager= LinearLayoutManager(activity as Context)
        initValues()
        rvFaq.adapter=FaqAdapter(activity as Context,menuList)
        rvFaq.adapter!!.notifyDataSetChanged()
        return view
    }
    fun initValues() {
        menuList.add(Pair("Q: I want to return my Order! What do I do?","A: If you are not 100% satisfied with your Order, Send us a message on Customer care chat or call us at (+123) 28549763943 Toll-Free."))
        menuList.add(Pair("Q: Why don't you deliver to me?","A: We only deliver to addresses 3km or less from the restaurant currently."))
        menuList.add(Pair("Q: How long can Refunds take?","A: A refund normally takes place between 0-3 bank days."))
        menuList.add(Pair("Q: I gave the wrong Order, Can I change the items?","A: Yes, Unless the order is already out for delivery, Give us a call at (+123) 28549763943 Toll-Free."))
        menuList.add(Pair("Q: When Can I Order?","A: Our services are available 24x7 excluding public holidays."))
        menuList.add(Pair("Q: Why is my order taking so long?","A: Sorry for the inconvenience,Contact us on (+123) 28549763943 Toll-Free. "))
        menuList.add(Pair("Q: How do you guys make money?","A: We make our income from advertising and affiliate marketing."))
        menuList.add(Pair("Q: What payment modes are available?","A: We have Cash,Debit/Credit Card,UPI and Voucher payment options."))
        menuList.add(Pair("Q: Why is the App is broken for me?","A: We're sorry to hear that, Please raise an issue here"))
    }

}
