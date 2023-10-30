package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.passwords.DB_Passwords.MyDatabaseHelper
import com.example.passwords.DB_Passwords.Password
import com.example.passwords.View_Passwords.MyAdapter

class update_password : AppCompatActivity() {
    lateinit var password : EditText
    lateinit var password1: EditText
    lateinit var proceed : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)
        password = findViewById(R.id.password)
        password1 = findViewById(R.id.password1)
        proceed = findViewById(R.id.button_proceed)

        val platform = intent.getStringExtra("platform")
        val username = intent.getStringExtra("username")
        val oldpassword = intent.getStringExtra("password")

        proceed.setOnClickListener {
            if(password.text.toString().isEmpty())
            {
                Toast.makeText(this, "Enter Correct Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password1.text.toString().isEmpty())
            {
                Toast.makeText(this, "Enter Correct Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.text.toString().trim() != password1.text.toString().trim())
            {
                Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(password.text.toString().trim() == password1.text.toString().trim())
            {
                val newPassword = password.text.toString().trim()
                val dbHelper = MyDatabaseHelper(this)
                dbHelper.deletePassword(platform.toString())
                Toast.makeText(this, "Password Deleted", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,save_password1::class.java)
                startActivity(intent)
                finish()

            }
        }
    }
}