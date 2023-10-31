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
import java.security.SecureRandom
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class set_pin : AppCompatActivity() {

    lateinit var pin : EditText
    lateinit var proceed : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_pin)

        pin = findViewById(R.id.edt_pin)
        proceed = findViewById(R.id.button_proceed)

        val modulus = 10007
        val key = 7

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
}