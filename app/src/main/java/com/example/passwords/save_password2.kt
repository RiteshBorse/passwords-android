package com.example.passwords

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class save_password2 : AppCompatActivity() {
    lateinit var platform: TextView
    lateinit var username: TextView
    lateinit var password: TextView
    lateinit var updatePassword : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savepassword2)
        platform = findViewById(R.id.text_platform)
        username = findViewById(R.id.text_username)
        password = findViewById(R.id.text_password)
        updatePassword = findViewById(R.id.update_password)

        val plat = intent.getStringExtra("text_platform") // Use .text to set text
        val user = intent.getStringExtra("text_username")
        val pass = intent.getStringExtra("text_password")

        platform.text = "$plat"
        username.text = "UserName : $user"
        password.text = "Password : $pass"

        updatePassword.setOnClickListener {
            val intent = Intent(this@save_password2,update_password::class.java)
            intent.putExtra("platform",plat)
            intent.putExtra("username",user)
            intent.putExtra("password",pass)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()

        // Finish the current activity and return to the previous activity
        finish()
    }
}
