package com.example.passwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.passwords.DB_Passwords.Password

class save_password2 : AppCompatActivity() {
    lateinit var platform: TextView
    lateinit var username: TextView
    lateinit var password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savepassword2)
        platform = findViewById(R.id.text_platform)
        username = findViewById(R.id.text_username)
        password = findViewById(R.id.text_password)

        val plat = intent.getStringExtra("text_platform") // Use .text to set text
        val user = intent.getStringExtra("text_username")
        val pass = intent.getStringExtra("text_password")

        platform.text = "$plat"
        username.text = "UserName : $user"
        password.text = "Password : $pass"
    }
}
