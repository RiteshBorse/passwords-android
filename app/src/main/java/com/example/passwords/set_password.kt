package com.example.passwords

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.ImageButton
import java.io.File

class set_password : AppCompatActivity() {

    lateinit var pass : EditText
    lateinit var passproceed : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_password)

        pass = findViewById(R.id.edt_password)
        passproceed = findViewById(R.id.button_proceed)

        val fileName3 = "passwords1.txt"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file3 = File(storageDir, fileName3)

        if(!file3.exists())
        {
            file3.createNewFile()
        }
        if(file3.readText().isEmpty())
        {
            file3.delete()
            file3.createNewFile()
        }

        if(file3.exists() && !file3.readText().isEmpty())
        {
            val intent = Intent(this@set_password,confirm_password::class.java)
            startActivity(intent)
            finish()
        }

        passproceed.setOnClickListener {
            file3.writeText(pass.text.toString().trim())
            val intent = Intent(this@set_password,confirm_password::class.java)
            startActivity(intent)
            finish()
        }
    }
}