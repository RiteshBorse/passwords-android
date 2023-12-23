package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import java.io.File
import androidx.activity.BackEventCompat
import java.nio.charset.Charset
import java.util.Base64

class confirm_password : AppCompatActivity() {

    lateinit var pass1 : EditText
    lateinit var passproceed2 : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_password)

        pass1= findViewById(R.id.edt_password1)
        passproceed2 = findViewById(R.id.button_proceed1)

        val fileName4 = "passwords1.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file4 = File(storageDir, fileName4)

        if(!file4.exists())
        {
            val intent = Intent(this@confirm_password,set_password::class.java)
            startActivity(intent)
            finish()
        }
        val fileName5 = "passwords_pass2.txt"
        val storageDir1 = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file5 = File(storageDir1, fileName5)
        if(file5.exists())
        {
            if(file4.readText().toString() == file5.readText().toString())
            {
                val intent = Intent(this@confirm_password,home::class.java)
                startActivity(intent)
                finish()
            }
            else if(file4.readText().toString() != file5.readText().toString())
            {
                file5.delete()
            }
        }
        if(!file5.exists())
        {
            file5.createNewFile()
        }
        if(file5.readText().isEmpty())
        {
            file5.delete()
            file5.createNewFile()
        }
        passproceed2.setOnClickListener {

            file5.writeText(encode(pass1.text.toString().trim()))
            if(file4.readText().toString() == file5.readText().toString())
            {
                val intent = Intent(this@confirm_password,home::class.java)
                startActivity(intent)
                Toast.makeText(this, "Password Set Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            else if(file4.readText().toString() != file5.readText().toString())
            {
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
                file5.delete()
                file4.delete()
                val intent = Intent(this@confirm_password,set_password::class.java)
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