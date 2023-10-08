package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class home : AppCompatActivity() {
    lateinit var addpass : ImageButton
    lateinit var viewpass : ImageButton
    lateinit var about : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addpass = findViewById(R.id.button_addpassword)
        viewpass = findViewById(R.id.button_viewpassword)
        about = findViewById(R.id.button_about)

        addpass.setOnClickListener {
            val intent = Intent(this@home,addpassword::class.java)
            startActivity(intent)
        }

        viewpass.setOnClickListener {
            val intent = Intent(this@home,addpassword::class.java)
            startActivity(intent)
        }

        about.setOnClickListener {
            val intent = Intent(this@home,addpassword::class.java)
            startActivity(intent)
        }
    }
}