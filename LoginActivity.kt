package com.rebel.foodrunner.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.rebel.foodrunner.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("Food Preferences", Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean("isLoggedIn",false))
            homeActivityIntent(
                sharedPreferences.getString("phone",null),
                sharedPreferences.getString("pass",null)
            )
        setContentView(R.layout.activity_login)
        login.setOnClickListener {
            signIn()
        }
        fp.setOnClickListener{
            forgotPass()
        }
        register.setOnClickListener {
            signUp()
        }
    }
    fun signUp() = startActivity(Intent(this,
        RegistrationActivity::class.java))
    fun forgotPass() = startActivity(Intent(this,
        ForgetActivity::class.java))
    fun signIn(){
        val phone=etMobileNumber.text.toString()
        val pass=etPass.text.toString()
        if(phone.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Enter Phone number or PassWord", Toast.LENGTH_SHORT).show()
    }
        else if(phone == sharedPreferences.getString("phone",null)&& pass == sharedPreferences.getString("pass",null)){
            Toast.makeText(this,"Logging In...",Toast.LENGTH_SHORT).show()
            homeActivityIntent(
                phone,
                pass
            )
            setLoggedIn()
        }else{
            Toast.makeText(this,"Wrong Password or Phone Number",Toast.LENGTH_SHORT).show()
        }
    }
    fun setLoggedIn() =sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
    fun homeActivityIntent(phone:String?,pass:String?) {
        try {
            val intent=Intent(this,
                HomeActivity::class.java)
            intent.putExtra("phone",phone)
            intent.putExtra("pass",pass)
            startActivity(intent)
            finish()
        }catch (e:Exception){
            Toast.makeText(this,"An Error has occurred! Try Again",Toast.LENGTH_SHORT).show()
            sharedPreferences.edit().clear().apply()
        }
    }
    fun signUpIntent(view: View) =startActivity(Intent(this,
        RegistrationActivity::class.java))

    fun forgotPasswordIntent(view: View) =startActivity(Intent(this,
        ForgetActivity::class.java))
}
