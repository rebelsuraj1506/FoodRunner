package com.rebel.foodrunner.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.rebel.foodrunner.R

class FriendActivity : AppCompatActivity() {
    lateinit var te: TextView
    lateinit var tr: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        te=findViewById(R.id.te)
        tr=findViewById(R.id.tr)
        te.text = intent.getStringExtra("Value2")
        tr.text = intent.getStringExtra("Value3")
    }
}
