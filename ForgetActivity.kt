package com.rebel.foodrunner.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.rebel.foodrunner.R

class ForgetActivity : AppCompatActivity() {
    lateinit var etMobileNumber: EditText
    lateinit var etEmail:EditText
    lateinit var nxt:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget)
        etMobileNumber=findViewById(R.id.etMobileNumber)
        etEmail=findViewById(R.id.etEmail)
        nxt=findViewById(R.id.nxt)
        nxt.setOnClickListener{
            val intent = Intent( this@ForgetActivity, FriendActivity::class.java)
            val mob: String = etMobileNumber.text.toString()
            val email: String = etEmail.text.toString()
            intent.putExtra("Value2", mob)
            intent.putExtra("Value3", email)
            startActivity(intent)
        }
    }
}
