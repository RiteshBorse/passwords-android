package com.example.passwords

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import androidx.activity.BackEventCompat
import java.nio.charset.Charset
import java.util.Base64

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
           file.writeText(encode(pin.text.toString().trim()))
            if(file.readText().isNotEmpty())
            {
                val intent = Intent(this@set_pin,confirm_pin::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                pin.setHintTextColor(Color.RED)
                Toast.makeText(this,"Enter a Pin",Toast.LENGTH_SHORT).show()
            }

        }

    }
    fun encode(text : String) : String
    {
        return Base64.getEncoder().encodeToString(text.toByteArray(Charsets.UTF_8))
    }
    fun decode(text : String) : String
    {
        return String(Base64.getDecoder().decode(text), Charsets.UTF_8)
    }
}