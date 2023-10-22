package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.passwords.DB_Passwords.MyDatabaseHelper
import com.example.passwords.DB_Passwords.Password

class add_password : AppCompatActivity() {
    lateinit var name : EditText
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var password1 : EditText
    lateinit var proceed : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addpassword)
        name = findViewById(R.id.platform)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        password1 = findViewById(R.id.password1)
        proceed = findViewById(R.id.button_proceed)

        proceed.setOnClickListener {
            if(name.text.isEmpty())
            {
                Toast.makeText(this, "Enter the name of Platform", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(username.text.isEmpty())
            {
                Toast.makeText(this, "Enter the Username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.text.isEmpty())
            {
                Toast.makeText(this, "Enter the Password", Toast.LENGTH_SHORT).show()
            }
            else if(password1.text.isEmpty())
            {
                Toast.makeText(this, "Enter the Password", Toast.LENGTH_SHORT).show()
            }
            else
            {
                if(password.text.toString().trim() != password1.text.toString().trim())
                {
                    Toast.makeText(this, "Password does not Match", Toast.LENGTH_SHORT).show()
                }
                else if(password.text.toString().trim() == password1.text.toString().trim())
                {
                    val passwords = Password(0,name.text.toString(), username.text.toString(),password.text.toString())
                    val dbHelper = MyDatabaseHelper(this)
                    dbHelper.insertPassword(passwords)
                    Toast.makeText(this, "Password Saved Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@add_password,home::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }


    }


}