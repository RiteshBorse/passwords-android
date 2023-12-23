package com.example.passwords

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import java.io.File
import java.util.Base64
import androidx.activity.BackEventCompat
import java.nio.charset.Charset

class confirm_pin : AppCompatActivity() {

    lateinit var pin1 : EditText
    lateinit var proceed : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_pin)

        pin1 = findViewById(R.id.edt_pin1)
        proceed = findViewById(R.id.button_proceed)

        val fileName = "passwords_pin.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(storageDir, fileName)

        if(!file.exists())
        {
            val intent = Intent(this@confirm_pin,set_pin::class.java)
            startActivity(intent)
            finish()
        }

        val fileName1 = "passwords_pin1.txt"
        val storageDir1 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file1 = File(storageDir1, fileName1)
        if(file1.exists())
        {
            if(file.readText().toString() == file1.readText().toString())
            {
                val intent = Intent(this@confirm_pin,set_password::class.java)
                startActivity(intent)
                finish()
                //Toast.makeText(this, "Pin Set Successfully", Toast.LENGTH_SHORT).show()
            }
            else if(file.readText().toString() != file1.readText().toString())
            {
                file1.delete()
            }
        }
        if(!file1.exists())
        {
            file1.createNewFile()
        }
        if(file1.readText().isEmpty())
        {
            file1.delete()
            file1.createNewFile()
        }
        proceed.setOnClickListener {

            file1.writeText(encode(pin1.text.toString().trim()))
            if(file.readText().toString() == file1.readText().toString())
            {
                val intent = Intent(this@confirm_pin,set_password::class.java)
                startActivity(intent)
                Toast.makeText(this, "Pin Set Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            else if(file.readText().toString() != file1.readText().toString())
            {
                Toast.makeText(this, "Pin do not match", Toast.LENGTH_SHORT).show()
                file1.delete()
                file.delete()
                val intent = Intent(this@confirm_pin,set_pin::class.java)
                startActivity(intent)
                finish()
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