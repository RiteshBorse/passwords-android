package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class home : AppCompatActivity() {
    lateinit var addPassword : ImageButton
    lateinit var viewPassword : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addPassword = findViewById(R.id.button_addpassword)
        viewPassword = findViewById(R.id.button_viewpassword)

        addPassword.setOnClickListener{
            val intent = Intent(this@home,add_password::class.java)
            startActivity(intent)
        }
        viewPassword.setOnClickListener{
            val intent = Intent(this@home,save_password1::class.java)
            startActivity(intent)
        }
    }
}