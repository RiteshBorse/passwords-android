package com.example.passwords

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import java.io.File

class set_pin : AppCompatActivity() {

    lateinit var pin : EditText
    lateinit var proceed : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin)

        pin = findViewById(R.id.edt_pin)
        proceed = findViewById(R.id.button_proceed)

        val fileName = "passwords_pin.txt"
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
            val intent = Intent(this@set_pin,confirm_pin::class.java)
            startActivity(intent)
            finish()
        }

        proceed.setOnClickListener {
            file.writeText(pin.text.toString().trim())
            val intent = Intent(this@set_pin,confirm_pin::class.java)
            startActivity(intent)
            finish()
        }

    }
}