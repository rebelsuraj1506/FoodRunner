package com.rebel.foodrunner.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.rebel.foodrunner.R
import com.rebel.foodrunner.fragment.*

class HomeActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    var previousMenuItem: MenuItem?=null
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_result)
        sharedPreferences=getSharedPreferences("Food Preferences", Context.MODE_PRIVATE)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()
        openMenu()
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@HomeActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            if(previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it
            when(it.itemId){
                R.id.menu ->{
                    openMenu()
                    drawerLayout.closeDrawers()}
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        ProfileFragment()
                    )
                        .commit()
                    supportActionBar?.title="Profile"
                    drawerLayout.closeDrawers()}
                R.id.favRes ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        FavResFragment()
                    )
                        .commit()
                    supportActionBar?.title="Favourite Restaurants"
                    drawerLayout.closeDrawers()}
                R.id.faqs ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frame,
                        FaqsFragment()
                    )
                        .commit()
                    supportActionBar?.title="FAQs"
                    drawerLayout.closeDrawers()}
                R.id.logout ->{
                    Log.e("foodflix","log out pressed")
                    AlertDialog
                        .Builder(this)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure You want to log out?")
                        .setPositiveButton("Yes") { dialog, whichButton ->
                            //log out
                            sharedPreferences.edit().putBoolean("isLoggedIn",false).apply()
                            startActivity(
                                Intent(this,
                                LoginActivity::class.java)
                            )
                            finishAffinity()
                            dialog.dismiss()
                        }
                        .setNegativeButton("No"){ dialog, whichButton ->
                            dialog.dismiss()
                        }
                        .show()}
            }
            return@setNavigationItemSelectedListener true }
    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
    fun openMenu(){
        val fragment= MenuFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()
        supportActionBar?.title="All Retaraunts"
    }
    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.frame)
        when(frag){
            !is MenuFragment ->openMenu()
            else->super.onBackPressed()
        }
    }
}
