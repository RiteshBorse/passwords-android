package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import java.io.File

class set_password : AppCompatActivity() {

    lateinit var password : EditText
    lateinit var proceed : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_password)

        password = findViewById(R.id.edt_password)
        proceed = findViewById(R.id.button_proceed)

        val fileName = "passwords1.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(storageDir, fileName)

        if(!file.exists())
        {
            file.createNewFile()
        }
        if(file.readText().isEmpty())
        {
            file.delete()
            file.createNewFile()
        }

        if(file.exists() && !file.readText().isEmpty())
        {
            val intent = Intent(this@set_password,confirm_password::class.java)
            startActivity(intent)
            finish()
        }

        proceed.setOnClickListener {
            file.writeText(password.text.toString().trim())
            val intent = Intent(this@set_password,confirm_password::class.java)
            startActivity(intent)
            finish()
        }
    }
}