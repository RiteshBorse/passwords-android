package com.example.passwords

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class home : AppCompatActivity() {
    lateinit var addPassword : ImageButton
    lateinit var viewPassword : ImageButton
    lateinit var about : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addPassword = findViewById(R.id.button_addpassword)
        viewPassword = findViewById(R.id.button_viewpassword)
        about = findViewById(R.id.about)

        addPassword.setOnClickListener{
            val intent = Intent(this@home,add_password::class.java)
            startActivity(intent)
        }
        viewPassword.setOnClickListener{
            val intent = Intent(this@home,save_password1::class.java)
            startActivity(intent)
        }
        about.setOnClickListener{
            val intent = Intent(this@home,about::class.java)
            startActivity(intent)
        }
    }
}