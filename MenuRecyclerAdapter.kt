package com.rebel.foodrunner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rebel.foodrunner.R
import com.rebel.foodrunner.model.Restaurant
import com.squareup.picasso.Picasso

class MenuRecyclerAdapter(val context: Context, val resList: ArrayList<Restaurant>): RecyclerView.Adapter<MenuRecyclerAdapter.MenuViewHolder>() {
    class MenuViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nameRecipe:TextView=view.findViewById(R.id.nameRecipe)
        val rupeeSymbol:TextView=view.findViewById(R.id.rupeeSymbol)
        val imgRecipe:ImageView=view.findViewById(R.id.imgRecipe)
        val resRating:TextView=view.findViewById(R.id.resRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_home_single_row,parent,false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val restaurant=resList[position]
        holder.nameRecipe.text=restaurant.name
        holder.resRating.text= restaurant.rating
        holder.rupeeSymbol.text= ("₹ "+restaurant.price+" per person")
        Picasso.get().load(restaurant.image).into(holder.imgRecipe)
    }


}