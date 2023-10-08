package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import java.io.File

class confirm_password : AppCompatActivity() {

    lateinit var password1 : EditText
    lateinit var proceed : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_password)

        password1= findViewById(R.id.edt_password1)
        proceed = findViewById(R.id.button_proceed1)

        val fileName1 = "passwords1.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file1 = File(storageDir, fileName1)

        if(!file1.exists())
        {
            val intent = Intent(this@confirm_password,set_password::class.java)
            startActivity(intent)
            finish()
        }
        val fileName2 = "passwords2.txt"
        val storageDir1 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file2 = File(storageDir1, fileName2)
        if(file2.exists())
        {
            if(file1.readText().toString() == file2.readText().toString())
            {
                val intent = Intent(this@confirm_password,home::class.java)
                startActivity(intent)
                finish()
            }
            else if(file1.readText().toString() != file2.readText().toString())
            {
                file2.delete()
            }
        }
        if(!file2.exists())
        {
            file2.createNewFile()
        }
        if(file2.readText().isEmpty())
        {
            file2.delete()
            file2.createNewFile()
        }
        proceed.setOnClickListener {

            file2.writeText(password1.text.toString().trim())
            if(file1.readText().toString() == file2.readText().toString())
            {
                val intent = Intent(this@confirm_password,home::class.java)
                startActivity(intent)
                Toast.makeText(this, "Password Set Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            else if(file1.readText().toString() != file2.readText().toString())
            {
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
                file2.delete()
                file1.delete()
                val intent = Intent(this@confirm_password,set_password::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}