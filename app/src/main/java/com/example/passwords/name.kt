package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import java.io.File

class name : AppCompatActivity() {

    lateinit var name : EditText
    lateinit var proceed : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        name = findViewById(R.id.edt_name)
        proceed = findViewById(R.id.button_proceed)

        val fileName = "passwords_name.txt"
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
            val intent = Intent(this@name,set_pin::class.java)
            startActivity(intent)
            finish()
        }

        proceed.setOnClickListener {
            file.writeText(name.text.toString().trim())
            val intent = Intent(this@name,set_pin::class.java)
            startActivity(intent)
            finish()
        }

    }
}